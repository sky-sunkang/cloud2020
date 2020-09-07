package com.sunkang.alibabapayment;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 支付
 */
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/peyment/{id}")
    public String peyment(@PathVariable(value = "id") Long id) {
        return StrUtil.format("alibaba payment port:{} id:{}", port, id);
    }
}
