package com.cloud.gatewayzuul.controller;

import com.cloud.gatewayzuul.feign.Oauth2Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zuyunbo
 */
@Slf4j
@RestController
public class TokenController {

    @Autowired
    private Oauth2Client oauth2Client;
    /**
     * 系统登陆<br>
     * 根据用户名登录<br>
     * 采用oauth2密码模式获取access_token和refresh_token
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/user/login")
    public Map<String, Object> login(String username, String password) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put(OAuth2Utils.GRANT_TYPE, "password");
        parameters.put(OAuth2Utils.CLIENT_ID, "app");
        parameters.put("client_secret", "stuy");
        parameters.put(OAuth2Utils.SCOPE, "select");
        parameters.put("username", username);
        parameters.put("password", password);
        Map<String, Object> tokenInfo = oauth2Client.postAccessToken(parameters);
        return tokenInfo;
    }

}
