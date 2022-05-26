package org.example.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("paypal")
@RequestMapping("/paypal")
public class PaypalCallbackController {

    @GetMapping("/callback")
    public String paypalCallback() {
        System.out.println("paypal回调！！");
        return "index";
    }
}
