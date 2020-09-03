package com.sunkang.orderServiceZk.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 测试已zookeeper为注册中心的访问形式
 */
@RestController
public class PlaymentController {

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/testZk")
    public String testZk(){
        return restTemplate.getForObject("http://playment-service-zk/testZk",String.class);
    }
}
