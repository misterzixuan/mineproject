package com.multipartdatasource.business;

import com.multipartdatasource.annotation.MineDataSource;
import org.springframework.stereotype.Component;


@Component
public class AopTest {

    @MineDataSource
    public void test(String name,String like){
        System.out.println(name);
        System.out.println(like);
    }
}
