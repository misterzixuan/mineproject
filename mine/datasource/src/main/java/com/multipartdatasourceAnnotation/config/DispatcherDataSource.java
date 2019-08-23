package com.multipartdatasourceAnnotation.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Component
public class DispatcherDataSource {

    @Bean
    @Primary
    public DynamicDataSource dataSource(@Qualifier(value = "mineSource") DataSource mineSource, @Qualifier(value = "mine1Source") DataSource mine1Source) {

        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceNames.MINE, mineSource);
        targetDataSources.put(DataSourceNames.MINE1, mine1Source);
        return new DynamicDataSource( mine1Source, targetDataSources);
    }
}
