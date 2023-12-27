package org.example.wtp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lock")
public class LockController {
    final Class<LockController> lockControllerClass = LockController.class;

    @GetMapping
    public void lock1() {
        synchronized (lockControllerClass) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t线程");
        }
    }
}
