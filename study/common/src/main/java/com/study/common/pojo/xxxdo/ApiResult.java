package com.study.common.pojo.xxxdo;

import lombok.Data;

@Data //这个注解是lombok的工具类，不用get set ，idea使用需要加lombok插件 和引入jar包
public class ApiResult<T> {

    //默认状态码200  其实就是一个工具类存的数字 替换成数字就行了
    private int code = 200;

    private String message;

    private T data;

    public ApiResult() {
    }

    public ApiResult(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
