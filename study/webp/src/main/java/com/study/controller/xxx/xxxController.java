package com.study.controller.xxx;

import com.study.business.xxx.impl.xxxServiceImpl;
import com.study.common.pojo.xxxdo.ErrorInfo;
import com.study.common.pojo.xxxdo.condition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("xxx")
public class xxxController {


    @Autowired
    xxxServiceImpl xxxService;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    condition c;

    @RequestMapping("findxxx")
    @ResponseBody
    public Object findxxx(){
       return  xxxService.get();
    }

    @RequestMapping("error")
    @ResponseBody
    public Object error(ErrorInfo errorInfo)
    {
        System.out.println(c);
        System.out.println(errorInfo);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("1044984012@qq.com");
        message.setTo("122362203@qq.com");
        message.setSubject("主题：简单邮件1");
        message.setText("测试邮件内容1");
        mailSender.send(message);
        mailSender.send(message);
        return "没有异常";
    }

}
