package com.multipartdatasourceAnnotation.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MineDataSource {
    String value() default "";
}
