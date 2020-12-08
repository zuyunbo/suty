package com.cloud.userauth.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class LoginConfiguration implements WebMvcConfigurer {

    @Bean
    public HandlerInterceptor getMyInterceptor() {
        System.out.println("启动拦截器");
        return new HandlerInterceptor() {
        };
    }

    // 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
    // addPathPatterns 用于添加拦截规则 ， 先把所有路径都加入拦截， 再一个个排除
    // addPathPatterns("/**") 表示拦截所有的请求，
    // excludePathPatterns("/login", "/register") 表示除了登陆与注册之外，因为登陆注册不需要登陆也可以访问
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getMyInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/", "/index.html", "/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**",
                        "classpath:/META‐INF/resources/","classpath:/resources/","classpath:/static/","classpath:/public/",
                        "classpath:/META‐INF/resources/templates/","classpath:/resources/templates/");
    }

    // 这个方法是用来配置静态资源的，比如html，js，css，等等
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        // 注意如果filePath是写死在这里，一定不要忘记尾部的/或者\\，这样才能读取其目录下的文件
        registry.addResourceHandler("/**").addResourceLocations(
                "classpath:/META-INF/resources/",
                "classpath:/resources/",
                "classpath:/static/",
                "classpath:/public/",
                "classpath:/webapp/",
                "classpath:/META‐INF/resources/templates/",
                "classpath:/resources/templates/",
                "classpath:/templates/");
    }
}
