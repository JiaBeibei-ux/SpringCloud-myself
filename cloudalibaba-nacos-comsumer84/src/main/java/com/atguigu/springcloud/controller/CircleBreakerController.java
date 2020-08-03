package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentFallbackService;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class CircleBreakerController {
    public static final String SERVER_URL = "http://nacos-payment-provider";

    @Resource
    public RestTemplate restTemplate;

    @GetMapping("/consumer/fallback/{id}")
    //@SentinelResource(value = "fallback")
    //@SentinelResource(value = "fallback",fallback = "handlerFallback")
    //@SentinelResource(value = "fallback",blockHandler = "blockHandler")
    @SentinelResource(value = "fallback",fallback = "handlerFallback",blockHandler = "blockHandler",exceptionsToIgnore = {IllegalArgumentException.class})
    public CommonResult<Payment> fallback(@PathVariable Long id) throws NullPointerException {
        CommonResult result = restTemplate.getForObject(SERVER_URL + "/paymentSql/" + id, CommonResult.class);
        if(id == 4){
            throw new IllegalArgumentException("参数非法。。。");
        }else if(result.getData() == null){
            throw new NullPointerException("空指针异常。。。");
        }
        log.info("Hello World!");
        return result;
    }
    public CommonResult handlerFallback(@PathVariable Long id,Throwable e){
        Payment payment = new Payment(id, null);
        return new CommonResult(444,"兜底的解决方案"+e.getMessage(),payment);
    }
   public CommonResult blockHandler(@PathVariable Long id, BlockException ex){
       Payment payment = new Payment(id, null);
       return new CommonResult(4445,"sentinel"+ex.getMessage(),payment);
   }

    @Resource
    private PaymentService paymentService;

    @GetMapping("/consumer/paymentSql/{id}")
    public CommonResult<Payment> paymentSql(@PathVariable("id") Long id){
        return paymentService.paymentSql(id);
    }

}
