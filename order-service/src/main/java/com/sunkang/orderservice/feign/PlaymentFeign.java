package com.sunkang.orderservice.feign;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.sunkang.apiCommon.entity.CommonResult;
import com.sunkang.apiCommon.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(serviceId = "playment-service", fallback = PaymentFallbackService.class)
public interface PlaymentFeign {
    /**
     * 获取流水号
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/payment/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable(value = "id") Long id);

    @PutMapping(value = "/createPayment")
    CommonResult createPayment(@RequestBody Payment payment);
}
