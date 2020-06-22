package com.cloud.managebackend.filters;

import com.example.commoncenter.util.JWTUtil;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with logindemo.
 * Author: dreamer-1
 * Email: zhong--lei@outllok.com
 * Date: 2018/5/13
 * Time: 下午2:58
 * Description:
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 在请求被处理之前调用
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 检查每个到来的请求对应的Header中是否有登录标识
//        String token = (String) request.getSession().getAttribute("token");
        String token = "21232f297a57a5a743894a0e4a801fc3";
        if (null == token) {
            // 未登录，重定向到登录页
            response.sendRedirect("/");
            return false;
        }

        String redisToken = redisTemplate.opsForValue().get(token);

        if (StringUtil.isEmpty(redisToken)) {
            // 未登录，重定向到登录页
            response.sendRedirect("/");
            return false;
        }

        //验证token
        boolean verify = JWTUtil.verify(redisToken);
        if (!verify) {
            // 未登录，重定向到登录页
            response.sendRedirect("/");
            return false;
        }

        //获取账户信息
        String username = JWTUtil.getUsername(redisToken);

        System.out.println("当前用户已登录，登录的用户名为： " + username);
        return true;
    }

    /**
     * 在请求被处理后，视图渲染之前调用
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在整个请求结束后调用
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
