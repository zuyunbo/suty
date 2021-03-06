package com.u.mybatisassembly.modle;

import java.lang.annotation.*;

/**
 * @author zuyunbo
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TableColumn {

    String value() default "";

}
