package com.u.mybatisassembly.modle;

import java.lang.annotation.*;
import java.text.Annotation;
/**
 * @author zuyunbo
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PrimaryKey {

    String seq() default "";

}
