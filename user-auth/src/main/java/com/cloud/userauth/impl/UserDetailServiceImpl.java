package com.cloud.userauth.impl;

import com.example.commoncenter.user.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Slf4j
@Service("UserDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SysUser sysUser = SysUser.builder().build();
        String finalSecret = new BCryptPasswordEncoder().encode("123456");

        if(username.equals("zuyunbo")){
             sysUser = SysUser.builder().username("zuyunbo").password(finalSecret).build();
        }

        return sysUser;
    }
}
