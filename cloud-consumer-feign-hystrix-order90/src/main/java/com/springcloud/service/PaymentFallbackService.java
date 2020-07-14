package com.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentFeignService {

    @Override
    public String paymentInfo(Integer id) {
        return "------PaymentFallbackService fallback paymentInfo,成功";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "------PaymentFallbackService fallback paymentInfo_Timeout,失败";
    }
}
