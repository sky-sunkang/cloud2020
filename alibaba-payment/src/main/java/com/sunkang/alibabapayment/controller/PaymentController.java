package com.sunkang.alibabapayment.controller;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 支付
 */
@RestController
@RefreshScope
public class PaymentController {

    @Value("${server.port}")
    private String port;

    @Value("${config.info}")
    private String configInfo;

    @GetMapping(value = "/payment/{id}")
    public String payment(@PathVariable(value = "id") Long id) {
        return StrUtil.format("alibaba payment port:{} id:{}", port, id);
    }

    @GetMapping(value = "/configInfo")
    public String configInfo(){
        return this.configInfo;
    }


    @GetMapping(value = "/currentLimit")
    public String limit(){
        return "我是测试限流方法服务 O(∩_∩)O哈哈~";
    }
}
