package com.atguigu.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;

public class CustomerBlockHandler {

    public static CommonResult handlerException(BlockException ex){
        return new CommonResult(444,ex.getClass().getCanonicalName()+"\t 全局的服务不可用1");
    }

    public static CommonResult handlerException2(BlockException ex){
        return new CommonResult(444,ex.getClass().getCanonicalName()+"\t 全局的服务不可用2");
    }
}
