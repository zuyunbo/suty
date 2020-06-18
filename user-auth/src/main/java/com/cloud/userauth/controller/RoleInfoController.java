package com.cloud.userauth.controller;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/user/roleInfo")
@Api(description = "角色")
public class RoleInfoController {

    @GetMapping(value = "/list")
    public Object list() {
        return "查询成功";
    }
}

