package org.example.seata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wangtaiping
 * 2022/3/8 0:45
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SeataServer {

    public static void main(String[] args) {
        new SpringApplication(SeataServer.class).run(args);
    }
}
