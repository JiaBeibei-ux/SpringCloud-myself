package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.OrderDao;
import com.atguigu.springcloud.domain.Order;
import com.atguigu.springcloud.service.AccountService;
import com.atguigu.springcloud.service.OrderService;
import com.atguigu.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private StorageService storageService;

    @Resource
    private AccountService accountService;

    @Override
    @GlobalTransactional
    public void create(Order order) {
         log.info("---->开始新建订单");
         //新建订单
         orderDao.create(order);
         log.info("---->订单微服务开始调用库存，做扣减count");
         //to do.....扣减库存
         storageService.decrease(order.getProductId(),order.getCount());
         log.info("---->订单微服务开始调用库存，做扣减end");

         log.info("---->订单微服务开始调用库存，做扣减money");
         //3扣减金额
         accountService.decrease(order.getUserId(),order.getMoney());
         log.info("---->订单微服务开始调用库存，做扣减end");

         //4、修改订单状态 从零开始到1完成
         log.info("---->修改订单状态开始");
         orderDao.update(order.getUserId(),0);
         log.info("---->修改订单状态结束");

         log.info("---->订单结束,O(n_n)O");
    }
}
