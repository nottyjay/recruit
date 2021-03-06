package com.d3code.recruit.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Nottyjay on 2016/11/29 0029.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.d3code.recruit"})
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
