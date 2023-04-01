package org.example.wtp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.example.wtp.mapper")
public class TestApplication {

    public static void main(String[] args) {
        new SpringApplication(TestApplication.class).run(args);
    }
}
