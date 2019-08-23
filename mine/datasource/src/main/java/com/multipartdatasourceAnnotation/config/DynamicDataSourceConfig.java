package com.multipartdatasourceAnnotation.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.multipartdatasourceAnnotation.entity.Mine1DataInfo;
import com.multipartdatasourceAnnotation.entity.MineDataInfo;
import org.apache.commons.beanutils.BeanMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

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
    Mine1DataInfo mine1DataInfo;

    @Autowired
    MineDataInfo mineDataInfo;


    @Bean
    public DataSource mineDataSource() {

        DataSource dataSource = null;
        try {
            dataSource = DruidDataSourceFactory.createDataSource(ObjToMap(mineDataInfo));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean
    public DataSource mine1DataSource() {

        DataSource dataSource = null;
        try {
            dataSource = DruidDataSourceFactory.createDataSource(ObjToMap(mine1DataInfo));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }

//    @Bean
//    @Primary
//    public DynamicDataSource dataSource() {
//
//        Map<Object, Object> targetDataSources = new HashMap<>();
//        targetDataSources.put(DataSourceNames.MINE, mineDataSource);
//        targetDataSources.put(DataSourceNames.MINE1, mine1DataSource);
//        return new DynamicDataSource(mineDataSource, targetDataSources);
//    }


    public BeanMap ObjToMap(Object obj) {
        if (obj == null) {
            return null;
        }
            return new org.apache.commons.beanutils.BeanMap(obj);
    }
}



