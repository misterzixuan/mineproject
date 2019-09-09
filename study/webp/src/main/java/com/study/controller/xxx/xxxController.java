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

import java.io.File;
import java.nio.file.Files;


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
        message.setCc("1760180402@qq.com");
        message.setSubject("主题：发给猪的一份信");
        message.setText("不知道猪能不能看懂！！");
        mailSender.send(message);
        mailSender.send(message);
        return "没有异常";
    }

}
