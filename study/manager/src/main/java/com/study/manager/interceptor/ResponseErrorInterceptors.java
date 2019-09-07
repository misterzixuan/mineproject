package com.study.manager.interceptor;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;


@ControllerAdvice
public class ResponseErrorInterceptors extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleBindException(
            BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorInfo = "参数：";
        BindingResult bindingResult = ex.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        for (ObjectError e : allErrors) {
            errorInfo += e.getDefaultMessage() + ",";
        }
        errorInfo = errorInfo.substring(0, errorInfo.lastIndexOf(","));

        return new ResponseEntity<Object>(errorInfo, status);
    }


    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object catchException(Exception e){
        return e.getMessage();
    }
}
