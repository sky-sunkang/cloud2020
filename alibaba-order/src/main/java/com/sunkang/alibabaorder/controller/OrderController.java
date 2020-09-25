package com.sunkang.alibabaorder.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.sunkang.alibabaorder.sentinel.SentinelFallbackHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 订单服务
 */
@RestController
@RefreshScope
public class OrderController {

    @Resource
    private RestTemplate restTemplate;

    @Value("${payment.addr}")
    private String paymentAddr;

    @Value("${config.info}")
    private String configInfo;


    @GetMapping(value = "/payment/{id}")
    @SentinelResource(value = "payment", blockHandlerClass = SentinelFallbackHandler.class, blockHandler = "defaultSentinelBlock",
            fallbackClass = SentinelFallbackHandler.class, fallback = "defaultSentinelFallback",exceptionsToIgnore = NullPointerException.class)
    public String payment(@PathVariable(value = "id", required = false) Long id) {

        //异常则进入fallback 但是NullPointerException不处理
        if (id == 0) {
            throw new NullPointerException("NullPointerException,id不能为0");
        }
        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常....");
        }
        return restTemplate.getForObject(paymentAddr + "/payment/" + id, String.class);
    }

    /**
     * 配置
     *
     * @return
     */
    @GetMapping(value = "/configInfo")
    public String configInfo() {
        return this.configInfo;
    }


    /**
     * 测试限流
     *
     * @return
     */
    @GetMapping(value = "/currentLimit")
    public String limit() {
        return restTemplate.getForObject(paymentAddr + "/currentLimit", String.class);
    }


    /**
     * 测试关联限流
     *
     * @return
     */
    @GetMapping(value = "/currentLimitB")
    public String currentLimitB() {

        return "我是测试限流方法服务B O(∩_∩)O哈哈~";
    }

    /**
     * 测试热点，热点必须根据名称来，不能根据地址
     * 会抛出错误，需要自己写降级兜底方法
     *
     * @param a
     * @param b
     * @return
     */
    @GetMapping(value = "/currentLimitAggs")
    @SentinelResource(value = "currentLimitAggs", blockHandler = "blockHandler")
    public String currentLimitAggs(@RequestParam(name = "a", required = false) String a, @RequestParam(value = "b", required = false) String b) {
//        if("2".equals(a)){
//            throw new RuntimeException("运行异常");
//        }
        return "我是测试限流方法服务currentLimitAggs O(∩_∩)O哈哈~ 参数：" + a + " " + b;
    }

    public String blockHandler(String p1, String p2, BlockException exception) {
        return "我被热点限流了";
    }

    /**
     * 测试降级，超过200毫米降级，最少1秒钟五个请求才会触发
     *
     * @return
     */
    @GetMapping(value = "/degradeRt")
    public String degradeRt() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "我是服务降级应用O(∩_∩)O哈哈~";
    }

    /**
     * 测试降级，一场比例20%，最少1秒钟五个请求才会触发
     *
     * @return
     */
    @GetMapping(value = "/degrade")
    public String degrade() {
        int a = 1 / 0;
        return "我是服务降级应用O(∩_∩)O哈哈~";
    }

    @GetMapping(value = "/authority")
    @SentinelResource(value = "authority")
    public String authority() {
        return "我在测试黑白名单O(∩_∩)O哈哈~";
    }

}
