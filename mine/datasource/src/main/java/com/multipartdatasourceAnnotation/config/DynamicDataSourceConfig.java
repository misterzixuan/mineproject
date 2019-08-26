package com.multipartdatasourceAnnotation.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.multipartdatasourceAnnotation.annotation.MineDataSource;
import com.multipartdatasourceAnnotation.dao.Userinfo;
import com.multipartdatasourceAnnotation.entity.Mine1DataInfo;
import com.multipartdatasourceAnnotation.entity.MineDataInfo;
import org.apache.commons.beanutils.BeanMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 配置多数据源
 * @author huojg
 */
@Configuration
public class DynamicDataSourceConfig {


    @Autowired
    MineDataInfo mineDataInfo;

    @Autowired
    Mine1DataInfo mine1DataInfo;


    @Bean
    @Primary
    public DynamicDataSource dataSource( ) {
        DataSource mine=mineSource();
        DataSource mine1=mine1DataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceNames.MINE, mine);
        targetDataSources.put(DataSourceNames.MINE1,mine1);
        return new DynamicDataSource( mine, targetDataSources);
    }



    public DataSource mineSource() {

        DataSource dataSource = null;
        try {
            dataSource = DruidDataSourceFactory.createDataSource(ObjToMap(mineDataInfo));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }


    public DataSource mine1DataSource() {

        DataSource dataSource = null;
        try {
            dataSource = DruidDataSourceFactory.createDataSource(ObjToMap(mine1DataInfo));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }


    public BeanMap ObjToMap(Object obj) {
        if (obj == null) {
            return null;
        }
            return new org.apache.commons.beanutils.BeanMap(obj);
    }
}



