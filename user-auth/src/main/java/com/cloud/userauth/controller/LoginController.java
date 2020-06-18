package com.cloud.userauth.controller;

import com.cloud.userauth.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@RestController
public class LoginController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 登录认证
     *
     * @param username 用户名
     * @param password 密码
     */
    @GetMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpServletRequest request) {
        if ("admin".equals(username) && "admin".equals(password)) {
            //生成token
            String token = JWTUtil.generateToken(username);
            //转换MD5
            String md5info = username + password;
            String md5 = DigestUtils.md5DigestAsHex(md5info.getBytes());
            //把MD5放入session
            request.getSession().setAttribute("token", md5);
            //数据放入redis
            redisTemplate.opsForValue().set(md5, token, JWTUtil.TOKEN_EXPIRE_TIME, TimeUnit.MILLISECONDS);
            return token;
        } else {
            return "error";
        }
    }

    /**
     * 刷新token
     */
    @GetMapping("/refreshToken")
    public String refreshToken(@RequestParam String userName) {
        //生成新的token
        String newToken = JWTUtil.generateToken(userName);
        redisTemplate.opsForValue().set(userName, newToken, JWTUtil.TOKEN_EXPIRE_TIME);
        return newToken;
    }

    @GetMapping("/")
    public String index() {
        return "auth-service: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}  