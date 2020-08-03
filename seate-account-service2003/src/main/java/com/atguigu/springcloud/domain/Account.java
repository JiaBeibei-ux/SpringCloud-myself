package com.atguigu.springcloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private Long id;
    private Long userId;
    private Long total;
    private BigDecimal used;
    private BigDecimal money;
    private BigDecimal residue;//订单状态 0代表创建中 1 已完结
}
