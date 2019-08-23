package com.multipartdatasourceAnnotation.dao;

import org.apache.ibatis.jdbc.RuntimeSqlException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface Userinfo {

    List<Map<String,Object>> findData() throws RuntimeSqlException;
}
