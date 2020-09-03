package com.sunkang.ribbonRule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.WeightedResponseTimeRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 该类不能放在启动类平级或者下级包
 * 即不能放在ComponentScan能扫描的地方
 */
@Configuration
public class MyRibbonRule {

    @Bean
    public IRule getIRule() {
        //默认轮询
        return new RoundRobinRule();
        //随机
//        return new RandomRule();
        //哪个几点响应更快，就优先
//        return new WeightedResponseTimeRule();
    }
}
