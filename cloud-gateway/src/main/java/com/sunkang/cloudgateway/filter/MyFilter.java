package com.sunkang.cloudgateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * gateway 过滤器，做权鉴或者日志之类
 */
@Component
public class MyFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println( exchange.getRequest().getPath());
//        异步写日志，MQ 
        return chain.filter(exchange);
    }

    //过滤器级别
    @Override
    public int getOrder() {
        return 0;
    }
}
