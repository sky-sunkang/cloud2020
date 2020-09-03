package com.sunkang.playmentservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.sunkang.apiCommon.entity.ResponseStatus;
import com.sunkang.playmentservice.dao.PaymentDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import com.sunkang.apiCommon.entity.Payment;
import javax.annotation.Resource;
import com.sunkang.apiCommon.entity.CommonResult;
@RestController
@DefaultProperties(commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")},defaultFallback = "paymentError")//服务降级超时时间
public class PaymentController {

    @Value(value = "${server.port}")
    private String port;

    @Resource
    private PaymentDao paymentDao;

    /**
     * 获取流水号
     * @param id
     * @return
     */
    @GetMapping(value = "/payment/{id}")
    //一般在调用端配置降级，服务端配置也可用
    @HystrixCommand
    public CommonResult<Payment> getPaymentById(@PathVariable(value = "id") Long id) {
        //不用try ，用了报错就不进入服务降级方法
        if (id < 0) {
            throw new RuntimeException();
        }
        try {
            Thread.sleep(2000);
            Payment payment=paymentDao.getPaymentById(id);
            return new CommonResult<Payment>(ResponseStatus.OK,"获取支付成功"+port,payment);
        }catch (Exception e){
            return new CommonResult<Payment>(ResponseStatus.ERROR,"获取支付失败"+port);
        }
    }


    /**
     * 插入订单
     * @param payment
     * @return
     */
    @PutMapping(value = "/createPayment")
    @HystrixCommand
    public CommonResult createPayment(@RequestBody Payment payment){
        try {
            int id=paymentDao.create(payment);
            return new CommonResult<Integer>(ResponseStatus.OK,"插入支付成功",id);
        }catch (Exception e){
            e.printStackTrace();
            return new CommonResult<Integer>(ResponseStatus.ERROR,"插入支付失败");
        }
    }


    @GetMapping(value = "/testPaymentTimeout")
    @HystrixCommand
    public String testPaymentTimeout(){
        try {
            Thread.sleep(3000);
            return "请求返回OK：" +System.currentTimeMillis();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "请求返回失败：" +System.currentTimeMillis();
        }
    }

    @GetMapping(value = "/testPaymentOk")
    @HystrixCommand
    public CommonResult<Payment>  testPaymentOk(){
        return new CommonResult<Payment>(ResponseStatus.ERROR, "请求返回OK：" +System.currentTimeMillis() );
    }

    /**
     * 服务降级方法
     *
     * @return
     */
    public CommonResult<Payment> paymentError() {
        return new CommonResult<Payment>(ResponseStatus.ERROR, "服务已经被降级或者熔断，o(╥﹏╥)o   " );
    }


}
