package com.multipartdatasourceAnnotation.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MineDataSource {
    String value() default "";
}
