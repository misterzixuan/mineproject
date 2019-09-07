package com.study.business.xxx.impl;

import com.study.business.xxx.xxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class xxxServiceImpl implements xxxService {

    @Autowired
    JpaRepository userinfo;


    @Override
    public Object get() {
        List all = userinfo.findAll();
        return all;
    }
}
