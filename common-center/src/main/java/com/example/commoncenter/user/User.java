package com.example.commoncenter.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author zuyunbo
 */
public class User {


    public static Authentication getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication;
     }

}
