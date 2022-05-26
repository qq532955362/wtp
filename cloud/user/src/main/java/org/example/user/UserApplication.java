package org.example.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wangtaiping
 * 2022/3/8 14:32
 */
@SpringBootApplication
//@EnableDiscoveryClient
public class UserApplication {

    public static void main(String[] args) {
        new SpringApplication(UserApplication.class).run(args);
    }
}
