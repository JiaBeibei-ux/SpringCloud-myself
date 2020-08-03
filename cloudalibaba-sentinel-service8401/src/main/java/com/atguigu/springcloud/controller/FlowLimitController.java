package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA(){
       /* try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
       log.info(Thread.currentThread().getName()+"\t"+"...testB");
        return "----->testA";
    }

    @GetMapping("/testB")
    public String testB(){
        return "----->testB";
    }

    @GetMapping("/testD")
    public String testD(){
        /*try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        log.info("testD 测试RT");
        int age = 10/0;
        return "----->testD";
    }

    @GetMapping("/testE")
    public String testE(){
        log.info("test 测试异常数");
        int age = 10/0;
        return "----->testE";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2) {
        //int age = 10/0;
        return "-----testHotKey";
    }
    public String deal_testHotKey(String p1, String p2, BlockException ex){
       return "deal_testHotKey";
    }
}
