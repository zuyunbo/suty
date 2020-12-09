package com.cloud.userauth.controller;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping("/test")
@Api(description = "角色222")
public class DemoController {

    @GetMapping(value = "/list")
    public Object list() {
        return "查询成功";
    }



    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public Principal getUser(Principal principal) {
        return principal;
    }

}

