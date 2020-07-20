package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * 负载均衡算法接口
 */
public interface LoadBalancer {
    ServiceInstance instances(List<ServiceInstance> serviceInstance);

}
