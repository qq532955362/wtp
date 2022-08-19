package org.example.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.core.ApplicationPushBuilder;
import org.example.user.listener.PrintHelloEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class TestController {

    @Resource
    private ApplicationEventPublisher publisher;

    @GetMapping("/test")
    public ResponseEntity<Boolean> test(){

        PrintHelloEvent printHelloEvent = new PrintHelloEvent("", 0L);

        publisher.publishEvent(printHelloEvent);
        publisher.publishEvent(printHelloEvent);

        log.info("{}线程主线,time:{}",Thread.currentThread().getName(),System.currentTimeMillis());

        return ResponseEntity.ok(Boolean.TRUE);
    }
}
