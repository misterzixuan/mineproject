package com.multipartdatasourceAnnotation.business;

import com.multipartdatasourceAnnotation.annotation.MineDataSource;
import com.multipartdatasourceAnnotation.config.DataSourceNames;
import com.multipartdatasourceAnnotation.dao.Userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
@MineDataSource("mine1")
public class AopTest {

    @Autowired
    Userinfo userinfo;

//    @MineDataSource("mine1")
    public void test(String name,String like){
        System.out.println(userinfo);
        List<Map<String, Object>> data = userinfo.findData();
        System.out.println(data.toString());
    }

//    @MineDataSource("mine")
    public void test2(String name,String like){
        System.out.println(userinfo);
        List<Map<String, Object>> data = userinfo.findData();
        System.out.println(data.toString());
    }
}
