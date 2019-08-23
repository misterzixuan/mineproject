package com;


import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RestController
public class testController {


    @RequestMapping("/test")
    public Object test(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpSession session = request.getSession(true);
        System.out.println(session);
        int localPort = request.getLocalPort();
        if(localPort==8080){
            session.setAttribute("port0","8080");
        }if(localPort==8081){
            session.setAttribute("port1","8081");
        }
        if(localPort==8082){
            session.setAttribute("port2","8082");
        }

        JSONObject result=new JSONObject();
        String value0= (String) session.getAttribute("port0");
        String value1= (String) session.getAttribute("port1");
        String value2= (String) session.getAttribute("port2");
        result.put("8080",value0);
        result.put("8081",value1);
        result.put("8082",value2);
        result.put("当前端口是",request.getLocalPort());
        System.out.println("获取session中的值"+request.getLocalPort());
        return result;
    }
}
