package com.cloud.userauth.controller;

import com.cloud.apimodel.entity.SysUser;
import com.cloud.apiservice.mapper.SysUserMapper;
import com.cloud.apiservice.service.SysUserService;
import com.example.commoncenter.base.BaseResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

@RestController
@Api(description = "用户1")
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    SysUserService sysUserService;


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