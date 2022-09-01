package org.example.user.config;


import cn.hutool.core.thread.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
public class MyThreadPoolConfig {


    @Bean("mysqlThreadPoolExecutor")
    public Executor mysqlThreadPoolExecutor() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNamePrefix("wtp-mysql-group").build();
        ThreadPoolExecutor mysqlThreadPoolExecutor = new ThreadPoolExecutor(
                2,
                Runtime.getRuntime().availableProcessors() / 2,
                15L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(),threadFactory);
        return mysqlThreadPoolExecutor;
    }

}
