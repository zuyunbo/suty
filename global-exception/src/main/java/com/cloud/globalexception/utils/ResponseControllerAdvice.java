package com.cloud.globalexception.utils;

import com.cloud.globalexception.pojo.ResultCode;
import com.cloud.globalexception.pojo.ResultVO;
import com.example.commoncenter.exception.IllegalParameterException;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 拦截Controller方法默认返回参数，统一处理返回值/响应体
 * @author 2u
 */
@RestControllerAdvice(basePackages = {"com"})
public class ResponseControllerAdvice implements ResponseBodyAdvice<Object> {

    private static final Log LOGGER = LogFactory.getLog(ResponseControllerAdvice.class);

    private static final String DEFAULT_PROVIDER_KEY = "default_provider_key";

    private static final ConcurrentMap<String, FilterProvider> PROVIDERS = new ConcurrentHashMap<>();

    static {
        PROVIDERS.put(DEFAULT_PROVIDER_KEY, new SimpleFilterProvider().setFailOnUnknownId(false));
    }


    @Override
    public Object beforeBodyWrite(Object returnValue, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
     /*   System.out.println("TestResponseBodyAdvice==>beforeBodyWrite:" + o.toString() + ","
                + methodParameter);*/
        return new ResultVO<>(ResultCode.SUCCESS.getCode(), "成功", returnValue);

/*        if (ClassUtils.isPrimitiveOrWrapper(Objects.requireNonNull(methodParameter.getMethod()).getReturnType())) {
            return returnValue;
        }
        if (returnValue == null && serverHttpRequest.getMethod() == HttpMethod.GET) {
            throw new IllegalParameterException("查询的对象不存在");
        }
        if (mediaType.isCompatibleWith(MediaType.APPLICATION_JSON)) {
            LOGGER.debug("=======>    启用自定义 FilterProvider ");
            assert returnValue != null;
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(returnValue);
            mappingJacksonValue.setFilters(getFilterProvider(methodParameter));

            return mappingJacksonValue;
        }
        return returnValue;*/
    }


    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        Class returnType = methodParameter.getMethod().getReturnType();
        return Object.class.isAssignableFrom(returnType);
    }


    public FilterProvider getFilterProvider(JsonResultFilter jsonResultFilter) {
        String key = jsonResultFilter.toString();

        BeanPropertyFilter propertyFilter = new BeanPropertyFilter();
        for (BeanFilter filter : jsonResultFilter.value()) {
            if(filter.type().isArray()){
                continue;
            }
            propertyFilter.mixin(filter.type()).includes(filter.includes()).excludes(filter.excludes());
        }

        SimpleFilterProvider provider = new SimpleFilterProvider().setFailOnUnknownId(false);
        provider.setDefaultFilter(propertyFilter);
        PROVIDERS.putIfAbsent(key, provider);
        return provider;
    }

    private FilterProvider getFilterProvider(MethodParameter methodParameter) {
        JsonResultFilter jsonResultFilter = Objects.requireNonNull(methodParameter.getMethod()).getAnnotation(JsonResultFilter.class);
        if (jsonResultFilter == null) {
            return PROVIDERS.get(DEFAULT_PROVIDER_KEY);
        }
        return getFilterProvider(jsonResultFilter);
    }

}
