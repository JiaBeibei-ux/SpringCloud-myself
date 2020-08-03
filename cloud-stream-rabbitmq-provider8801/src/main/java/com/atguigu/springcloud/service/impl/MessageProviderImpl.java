package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.MessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;
import java.util.UUID;

@EnableBinding(Source.class)//定义消息的推送管道 将channel和exchange绑定
public class MessageProviderImpl implements MessageProvider {
    //dao 没有

    @Resource
    private MessageChannel output;//消息发送通道

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        //构建消息
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("***serial: "+serial);
        return null;
    }
}
