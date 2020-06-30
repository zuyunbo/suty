package com.cloud.globalexception.utils;

import java.lang.annotation.*;

/**
 * @author 2u
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonResultFilter {

    BeanFilter[] value();

}