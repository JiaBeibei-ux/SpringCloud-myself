package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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
    // global fallback

    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value ="true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value ="10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value ="10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value ="60"),//失败率达到多少后跳闸

    })
    public String paymentCircuitBreaker(Integer id){
        if(id < 0){
            throw new RuntimeException("***id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();//UUID.random
        return  Thread.currentThread().getName()+"\t"+"调用成功，流水号:"+serialNumber;

    }
    public String paymentCircuitBreaker_fallback(Integer id){
        return "id 不能为负数，请稍后重试，/(TOT)/~~ id: "+id;
    }

}
