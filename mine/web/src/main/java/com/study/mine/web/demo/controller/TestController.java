package com.study.mine.web.demo.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TestController implements test{

    @RequestMapping("/test")
    public String test(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        System.out.println(request.getLocalPort());
        System.out.println(request.getRemotePort());
        System.out.println(request.getServerPort());
        return "使用的端口号是:"+request.getLocalPort();
    }
}
