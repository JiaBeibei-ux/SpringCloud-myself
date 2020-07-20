package com.atguigu.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    public String paymentInfo_OK(Integer id){
      return "线程池： "+Thread.currentThread().getName()+" paymentInfo_OK"+id;
    }
    @HystrixCommand(fallbackMethod = "paymentInfo_ERRORHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String paymentInfo_ERROR(Integer id){
        /*int timeNum = 5;*/
        int age = 10/0;
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池： "+Thread.currentThread().getName()+" paymentInfo_ERROR"+id;
    }
    public String paymentInfo_ERRORHandler(Integer id){
        return "线程池： "+Thread.currentThread().getName()+" paymentInfo_ERRORHandler"+id+"系统忙或者运行异常";
    }
}
