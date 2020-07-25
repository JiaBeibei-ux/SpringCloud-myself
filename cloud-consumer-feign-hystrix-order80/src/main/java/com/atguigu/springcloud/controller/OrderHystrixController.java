package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentInfo_OK(id);
        log.info("**result " + result);
        return result;
    }

   /* @HystrixCommand(fallbackMethod = "paymentInfo_ERRORHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
    })*/
    @HystrixCommand
    @GetMapping("/consumer/payment/hystrix/error/{id}")
    public String paymentInfo_ERROR(Integer id){
        /*int timeNum = 5;*/
        //int age = 10/0;
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池： "+Thread.currentThread().getName()+" paymentInfo_ERROR"+id;
    }
    public String paymentInfo_ERRORHandler(Integer id) {
        return "我是消费者80，服务方比较忙或自己运行出错！";
    }
    //下面是全局fallback
    public String payment_Global_FallbackMethod() {
        return "我是全局的异常处理信息，服务方比较忙或自己运行出错！";
    }
}
