package com.cloud.globalexception.utils;

import com.cloud.globalexception.config.NotResponseBody;
import com.cloud.globalexception.pojo.ResultVO;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 拦截Controller方法默认返回参数，统一处理返回值/响应体
 */
//@ControllerAdvice
@RestControllerAdvice(basePackages = {"com"})
public class ResponseControllerAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        /*System.out.println("TestResponseBodyAdvice==>beforeBodyWrite:" + o.toString() + ","
                + methodParameter);
        return o;*/
        return null;
    }

    //判定是 true / false 判定beforeBodyWrite 运行与否
    @Override
    public boolean supports(MethodParameter returnType, Class aClass) {
        // 如果接口返回的类型本身就是ResultVO那就没有必要进行额外的操作，返回false
        // 如果方法上加了我们的自定义注解也没有必要进行额外的操作
        return !(returnType.getParameterType().equals(ResultVO.class) || returnType.hasMethodAnnotation(NotResponseBody.class));
    }
}
