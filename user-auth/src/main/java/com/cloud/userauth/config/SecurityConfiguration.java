package com.cloud.userauth.config;


import com.example.commoncenter.constants.PermitAllUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.annotation.Resource;

/**
 *  @author 2u
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    public UserDetailsService userDetailsService;

    @Resource
    private BCryptPasswordEncoder passwordEncoder;
    /**
     * 全局用户信息<br>
     * 方法上的注解@Autowired的意思是，方法的参数的值是从spring容器中获取的<br>
     * 即参数AuthenticationManagerBuilder是spring中的一个Bean
     *
     * @param auth 认证管理
     * @throws Exception 用户认证异常信息
     */
    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    /**
     * springboot2.0 删除了原来的 plainTextPasswordEncoder
     * https://docs.spring.io/spring-security/site/docs/5.0.4.RELEASE/reference/htmlsingle/#10.3.2 DelegatingPasswordEncoder
     *
     */

    @Bean
    PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        AuthenticationManager manager = super.authenticationManagerBean();
        return manager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 放开权限的url
        http.authorizeRequests()
                .antMatchers(PermitAllUrl.permitAllUrl()).permitAll()
                .anyRequest().authenticated().and()
                .httpBasic().and().csrf().disable();
    }
}
