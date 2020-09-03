package com.sunkang.orderservice.feign;

import com.sunkang.apiCommon.entity.CommonResult;
import com.sunkang.apiCommon.entity.Payment;
import com.sunkang.apiCommon.entity.ResponseStatus;
import org.springframework.stereotype.Component;

/**
 * fegin的降级返回，如服务提供者停机会调用
 */
@Component
public class PaymentFallbackService implements PlaymentFeign {
    @Override
    public CommonResult<Payment> getPaymentById(Long id) {
        return new CommonResult<Payment>(ResponseStatus.ERROR, "支付服务异常，返回PaymentFallbackService");
    }

    @Override
    public CommonResult createPayment(Payment payment) {
        return new CommonResult<Payment>(ResponseStatus.ERROR, "支付服务异常，返回PaymentFallbackService");
    }
}
