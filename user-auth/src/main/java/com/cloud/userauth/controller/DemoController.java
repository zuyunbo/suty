package com.cloud.userauth.controller;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/test")
@Api(description = "角色222")
public class DemoController {

    @GetMapping(value = "/list")
    public Object list() {
        return "查询成功";
    }
}

