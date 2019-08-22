package com.study.mine.web.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.FileSystemResourceLoader;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        DemoApplication demoApplication=new DemoApplication();
        SpringApplication.run(DemoApplication.class, args);
    }

}
