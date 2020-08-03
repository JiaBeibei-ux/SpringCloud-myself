package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.domain.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;


@Mapper
public interface AccountDao {

    //扣钱
    void decrease(@Param("userId") Long userId, @Param("money") BigDecimal money);
}
