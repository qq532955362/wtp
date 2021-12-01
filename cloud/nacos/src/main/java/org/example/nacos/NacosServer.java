package org.example.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wangtaiping
 * 2021-11-9 00:17:07
 */
@SpringBootApplication(scanBasePackages = "com.alibaba.nacos")
public class NacosServer {
    public static void main(String[] args) {
        System.setProperty("nacos.standalone", "true");
        new SpringApplication(NacosServer.class).run(args);
    }
}
