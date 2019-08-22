package com.study.mine.web.demo;


import com.DemoApplication;
import com.multipartdatasource.business.AopTest;
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

    @Test
    public void dataSourceAopTest(){
        System.out.println(test);
        test.test("aaa","bbb");
    }
}
