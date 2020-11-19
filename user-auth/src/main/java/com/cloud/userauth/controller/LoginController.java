package com.cloud.userauth.controller;

import com.cloud.apimodel.entity.SysUser;
import com.cloud.apiservice.mapper.SysUserMapper;
import com.cloud.apiservice.service.SysUserService;
import com.cloud.userauth.model.UserView;
import com.cloud.userauth.utils.JWTUtil;
import com.example.commoncenter.base.BaseResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

@RestController
@Api(description = "用户1")
public class LoginController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    SysUserService sysUserService;

    /**
     * 登录认证
     *
     * @param userView 用户信息
     */
    @PostMapping("/login")
    @ApiOperation(value = "登陆")
    public BaseResponseUtil<SysUser> login(@RequestBody UserView userView, HttpServletRequest request) {

        String userName = userView.getUsername();
        String password = userView.getPassword();

        Predicate<SysUser> predicate = sys ->"1".equals(sys.getPassword());

        BaseResponseUtil<SysUser> valid = this
                .valid(sysUserService::selectByUserName, predicate,
                        "账号错误", userName);


        return valid;
       /* if (valid) {
            //生成token
            String token = JWTUtil.generateToken(userName);
            //转换MD5
            String md5 = DigestUtils.md5DigestAsHex(userName.getBytes());
            //把MD5放入session
            request.getSession().setAttribute("token", md5);
            //数据放入redis
            redisTemplate.opsForValue().set(md5, token, JWTUtil.TOKEN_EXPIRE_TIME, TimeUnit.MILLISECONDS);
            return md5;
        } else {
            return "error";
        }*/
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


    private <T> BaseResponseUtil<T> valid(Function<String, T> selectFunction, Predicate<T> predicate, String message, Object... arg) {
        for (Object obj : arg) {
            if (obj instanceof String) {
                Optional<T> opt = Optional.ofNullable(selectFunction.apply(String.valueOf(obj)));
                if(!opt.isPresent()){
                   return  new BaseResponseUtil<T>().constructResponseValid(BaseResponseUtil.FAILED, message, obj);
                }
                if(!predicate.test(opt.get())){
                    System.out.println("明显对不上");
                }
            }
        }
        return new BaseResponseUtil<T>().constructResponseValid(BaseResponseUtil.SUCCESS, message, null);
    }

}  