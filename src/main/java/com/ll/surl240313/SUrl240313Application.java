package com.ll.surl240313;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SUrl240313Application {

    public static void main(String[] args) {
        SpringApplication.run(SUrl240313Application.class, args);
    }

}
