package com.sunkang.alibabaorder.sentinel;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import org.springframework.web.bind.annotation.PathVariable;

import java.lang.reflect.UndeclaredThrowableException;

/**
 * 限流降级方法，
 * 必须带有原方法的参数
 * 同时满足配置限制和报错，则返回配置限制，即返回defaultSentinelBlock
 */
public class SentinelFallbackHandler {

    /**
     * 限流等配置策略导致
     * @param id
     * @param exception
     * @return
     */
    public static String defaultSentinelBlock(@PathVariable Long id,BlockException exception) {

        return "我被限流了o(╥﹏╥)o";
    }

    /**
     * 异常降级
     * @param id
     * @param exception
     * @return
     */
    public static String defaultSentinelFallback(@PathVariable Long id,Throwable exception) {

        return "我被降级了o(╥﹏╥)o";
    }
}
