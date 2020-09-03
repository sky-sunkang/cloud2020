package com.sunkang.orderservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {

    @Value("${profile}")
    private String profile;

    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/getConfigo")
    public String getConfigo(){
        return port+"  "+profile;
    }
}
