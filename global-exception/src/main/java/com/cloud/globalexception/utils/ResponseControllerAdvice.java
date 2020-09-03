package com.cloud.globalexception.utils;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 拦截Controller方法默认返回参数，统一处理返回值/响应体
 *
 * @author
 */

/*
@RestControllerAdvice(basePackages = {"com"})
*/
public class ResponseControllerAdvice implements ResponseBodyAdvice<Object> {

    private static final Log LOGGER = LogFactory.getLog(ResponseControllerAdvice.class);

    private static final String DEFAULT_PROVIDER_KEY = "default_provider_key";

    private static final ConcurrentMap<String, Map<String, Object>> PROVIDERS = new ConcurrentHashMap<>();

    static {
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("data", null);
        PROVIDERS.put(DEFAULT_PROVIDER_KEY, result);
    }

    @Override
    public Object beforeBodyWrite(Object returnValue, @NotNull MethodParameter methodParameter, MediaType mediaType, @NotNull Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        LOGGER.info("ResponseBodyAdvice==>beforeBodyWrite:" + returnValue.toString() + ","
                + methodParameter);

        if (serverHttpRequest.getMethod() == HttpMethod.GET && ObjectUtils.isEmpty(returnValue)) {
            return PROVIDERS.get(DEFAULT_PROVIDER_KEY);
        }else if (serverHttpRequest.getMethod() == HttpMethod.GET) {
            Map<String, Object> result = new HashMap<>();
            result.put("data", returnValue);
            return result;
        }
        System.out.println(methodParameter.getGenericParameterType());
        if (mediaType.isCompatibleWith(MediaType.APPLICATION_JSON) && methodParameter.getGenericParameterType()!=Object.class) {
            return getFilterProvider(methodParameter);
        }
        return returnValue;
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        Class returnType = Objects.requireNonNull(methodParameter.getMethod()).getReturnType();
        return Object.class.isAssignableFrom(returnType);
    }

    public Map<String, Object> getFilterProvider() {
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;
    }

    private Map<String, Object> getFilterProvider(MethodParameter methodParameter) {
        ResponseBody annotation = Objects.requireNonNull(methodParameter.getMethod()).getAnnotation(ResponseBody.class);
        if (annotation == null) {
            return PROVIDERS.get(DEFAULT_PROVIDER_KEY);
        }
        return getFilterProvider();
    }


    @ExceptionHandler(Exception.class)
    public Object ExceptionExceptionHandler(Exception e) {
        String defaultMessage = e.getMessage();
        LOGGER.error(defaultMessage);
        e.printStackTrace();
        Map<String, Object> result = new HashMap<>();
        result.put("msg", defaultMessage);
        return result;
    }


}
