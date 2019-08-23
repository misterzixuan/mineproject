package com.multipartdatasourceAnnotation.business;

import com.multipartdatasourceAnnotation.annotation.MineDataSource;
import com.multipartdatasourceAnnotation.config.DataSourceNames;
import com.multipartdatasourceAnnotation.dao.Userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public class AopTest {


    @MineDataSource(DataSourceNames.MINE)
    public void test(String name,String like){
//        System.out.println(userinfo);
//        List<Map<String, Object>> data = userinfo.findData();
//        System.out.println(data);
    }
}
