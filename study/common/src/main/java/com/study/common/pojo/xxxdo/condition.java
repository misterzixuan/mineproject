package com.study.common.pojo.xxxdo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;


@Component
@ConditionalOnProperty(prefix = "test",name = {"b"},havingValue = "c")
public class condition {
    private String b;


}
