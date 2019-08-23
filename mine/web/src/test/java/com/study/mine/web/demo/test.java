package com.study.mine.web.demo;


import com.DemoApplication;
import com.multipartdatasourceAnnotation.business.AopTest;
import com.multipartdatasourceAnnotation.dao.Userinfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={DemoApplication.class})// 指定启动类
public class test {

    @Autowired
    AopTest test;

    @Autowired
    Userinfo userinfo;
    @Test
    public void dataSourceAopTest(){
        System.out.println(userinfo);
        test.test("aaa","bbb");
    }
}
