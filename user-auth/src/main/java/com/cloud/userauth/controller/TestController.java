package com.cloud.userauth.controller;

import com.cloud.userauth.utils.JWTUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@RestController
@Api(description = "用户1")
public class TestController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 登录认证
     *
     * @param username 用户名
     * @param password 密码
     */
    @PostMapping("/login")
    @ApiOperation(value = "登陆")
    public String login(@RequestParam String username, @RequestParam String password, HttpServletRequest request) {
        if ("admin".equals(username) && "admin".equals(password)) {
            //生成token
            String token = JWTUtil.generateToken(username);
            //转换MD5
            String md5 = DigestUtils.md5DigestAsHex(username.getBytes());
            //把MD5放入session
            request.getSession().setAttribute("token", md5);
            //数据放入redis
            redisTemplate.opsForValue().set(md5, token, JWTUtil.TOKEN_EXPIRE_TIME, TimeUnit.MILLISECONDS);
            return md5;
        } else {
            return "error";
        }
    }

    /**
     * 刷新token
     */
    @GetMapping("/refreshToken")
    public String refreshToken(@RequestParam String userName, HttpServletRequest request) {
        //生成新的token
        String newToken = JWTUtil.generateToken(userName);
        //转换MD5
        String md5 = DigestUtils.md5DigestAsHex(userName.getBytes());
        redisTemplate.opsForValue().set(md5, newToken, JWTUtil.TOKEN_EXPIRE_TIME);
        //把MD5放入session
        request.getSession().setAttribute("token", md5);
        return md5;
    }

//    @RequestMapping("/")
//    public ModelAndView index() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("index");
//        return modelAndView;
//    }


}  