package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class PaymentController {
    @Value("server.port")
    private String serverPort;
    @GetMapping("/payment/consul")
    public String paymentConsul(){
        return "springCloud with consul:"+serverPort+"\t"+ UUID.randomUUID().toString();
    }
}
