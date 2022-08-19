package org.example.user.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PrintHelloListener implements ApplicationListener<PrintHelloEvent> {
    @Override
    public void onApplicationEvent(PrintHelloEvent printHelloEvent) {
        long timestamp = printHelloEvent.getTimestamp();
        log.error("当前线程:{},time:{}",Thread.currentThread().getName(),System.currentTimeMillis());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.error("{}线程休眠结束",Thread.currentThread().getName());
    }
}
