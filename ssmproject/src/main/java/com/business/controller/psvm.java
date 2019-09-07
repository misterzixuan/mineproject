package com.business.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class psvm {
    public static void main(String[] args) {
        FileSystemXmlApplicationContext fileSystemXmlApplicationContext=new FileSystemXmlApplicationContext("applicationContext-trans.xml");
    }
}
