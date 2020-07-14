package com.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;
import com.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
//设置统一fallback
//@DefaultProperties(defaultFallback = "")
public class OrderFeignController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/ok/{id}")
    public String paymentInfo(@PathVariable("id") Integer id){
        return paymentFeignService.paymentInfo(id);
    }

    @GetMapping("/consumer/payment/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")})
    public String paymentInfo_Timeout(@PathVariable("id") Integer id){
        return paymentFeignService.paymentInfo_Timeout(id);
    }

    public String paymentInfo_TimeoutHandler(@PathVariable("id") Integer id){
        return "线程池：    " + Thread.currentThread().getName() + "    90错误" + id;
    }
}
