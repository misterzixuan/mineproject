package com.study.common.pojo.xxxdo;

import com.study.common.pojo.xxxdo.validationgrop.one;
import com.study.common.pojo.xxxdo.validationgrop.two;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@GroupSequence({one.class,two.class,ErrorInfo.class})//不加  没家验证注解则不验证  加了 没见验证注解则按照此注解顺序验证,而且使用该注解的实体类也要放进去并且只能放在最后一位
public class ErrorInfo implements Serializable {

    @NotNull(message = "name不能为null",groups = {one.class})
    private String name;

    @NotNull(message = "age不能为null",groups = {two.class})
    private String age;
}


