package com.sunkang.playmentservicezk.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlaymentController {

    @Value(value = "${server.port}")
    private String port;

    @GetMapping(value = "/testZk")
    public String testZk(){

        return "zookeeper注册中心访问方式成功 "+port;
    }
}
