package org.example.user.config;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.AbstractApplicationContext;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class ListenerConfig {

    /**
     * 任务提交给线程池之后
     * 核心线程->阻塞队列->逐渐扩张到最大线程数
     *
     * @return
     */
    @Bean(value = "eventAsynThreadPool")
    public ThreadPoolExecutor eventAsynThreadPool() {
        ThreadPoolExecutor eventAsynThreadPool = new ThreadPoolExecutor(
                3,
                5,
                15,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(50),new ThreadFactoryBuilder().setNamePrefix("wtp-listener").build(),
                new ThreadPoolExecutor.AbortPolicy()
        );

        return eventAsynThreadPool;
    }

    /**
     * 事件广播器，发布事件的方式
     *
     * @see {@link org.springframework.context.event.SimpleApplicationEventMulticaster#multicastEvent(org.springframework.context.ApplicationEvent, org.springframework.core.ResolvableType)}
     *
     * @return
     */
    @Bean(AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME)
    public SimpleApplicationEventMulticaster wtpEventMulticaster(){
        SimpleApplicationEventMulticaster simpleApplicationEventMulticaster = new SimpleApplicationEventMulticaster();
        //使用自定义的线程池，spring中如无此配置，则使用当前线程执行事件(同步)
        simpleApplicationEventMulticaster.setTaskExecutor(eventAsynThreadPool());
        return simpleApplicationEventMulticaster;
    }

}
