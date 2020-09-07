package com.sunkang.alibabaorder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 订单服务
 */
@RestController
public class OrderController {

    @Resource
    private RestTemplate restTemplate;

    @Value("${payment.addr}")
    private String paymentAddr;


    @GetMapping(value = "/peyment/{id}")
    public String peyment(@PathVariable(value = "id") Long id) {
        return restTemplate.getForObject(paymentAddr+"/peyment/"+id,String.class);
    }
}
