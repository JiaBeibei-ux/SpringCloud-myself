package com.atguigu.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfig {
    @Bean//通过路由器访问百度
    public RouteLocator customRouterLocator(RouteLocatorBuilder locatorBuilder){
        RouteLocatorBuilder.Builder routes = locatorBuilder.routes();
        //https://news.baidu.com/guonei
        routes.route("path_route_atguigu",
                r ->r.path("/guonei").
                        uri("https://news.baidu.com/guonei")).build();
        routes.route("path_route_atguigu2",
                r ->r.path("/guoji").
                        uri("https://news.baidu.com/guoji")).build();
        return routes.build();
    }
}
