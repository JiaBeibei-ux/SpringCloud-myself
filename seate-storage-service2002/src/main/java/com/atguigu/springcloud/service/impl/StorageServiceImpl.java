package com.atguigu.springcloud.service.impl;


import com.atguigu.springcloud.dao.StorageDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class StorageServiceImpl implements com.atguigu.springcloud.service.StorageService {

    @Resource
    private StorageDao storageDao;
    @Override
    public void decrease(Long productId, Integer count) {
        log.info("---->storage service中扣减开始");
        storageDao.decrease(productId, count);
        log.info("---->storage service中扣减end");
    }
}