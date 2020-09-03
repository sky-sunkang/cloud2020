package com.sunkang.orderservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.sunkang.apiCommon.entity.CommonResult;
import com.sunkang.apiCommon.entity.Payment;
import com.sunkang.apiCommon.entity.ResponseStatus;
import com.sunkang.orderservice.feign.PlaymentFeign;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController

@DefaultProperties(commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),//服务降级超时时间
        @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),// 开启熔断（服务被熔断后，会在默认五秒钟时间后重新尝试恢复）
        @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), //请求10次
        @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"), //错误率60%
        @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000")// 时间范围
}, defaultFallback = "getPaymentByIdError")
public class PaymentController {

    @Value(value = "${payment.address}")
    private String paymentAddress;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private PlaymentFeign playmentFeign;

    @GetMapping(value = "/payment/{id}", produces = {"application/json;charset=UTF-8"})
    @HystrixCommand
    public CommonResult<Payment> getPaymentById(@PathVariable(value = "id") Long id) {

        //不用try ，用了报错就不进入服务降级方法
        if (id < 0) {
            throw new RuntimeException();
        }
        return playmentFeign.getPaymentById(id);
//        try {

//            CommonResult<Payment> commonResult=restTemplate.getForObject(this.paymentAddress+"payment/"+id, CommonResult.class);
//            return commonResult;
//        }catch (Exception e){
//            e.printStackTrace();
//            return new CommonResult<Payment>(ResponseStatus.ERROR,"获取支付失败");
//        }
    }


    /**
     * 插入订单
     *
     * @return
     */
    @PutMapping(value = "/createPayment", produces = {"application/json;charset=UTF-8"})
    public CommonResult<Integer> createPayment(@RequestBody Payment payment) {
        try {
            playmentFeign.createPayment(payment);
            return new CommonResult<Integer>(ResponseStatus.OK, "插入支付成功");
//            restTemplate.put(this.paymentAddress+"createPayment",payment);
//            return new CommonResult<Integer>(ResponseStatus.OK,"插入支付成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult<Integer>(ResponseStatus.ERROR, "插入支付失败");
        }
    }

    /**
     * 服务降级方法
     *
     * @return
     */
    public CommonResult<Payment> getPaymentByIdError(Throwable throwable) {
        return new CommonResult<Payment>(ResponseStatus.ERROR, "服务已经被降级或者熔断，o(╥﹏╥)o   " + throwable.toString());
    }
}
