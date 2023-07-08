package com.example.springdatatask1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableFeignClients
@EnableAsync
public class Springdatatask1Application {

    public static void main(String[] args) {
       SpringApplication.run(Springdatatask1Application.class, args);
    }

}
