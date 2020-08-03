package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService{

    public CommonResult<Payment> paymentSql(Long id){
       return new CommonResult<>(44444,"----PaymentFallbackService",new Payment(id,"error"));
    }

}
