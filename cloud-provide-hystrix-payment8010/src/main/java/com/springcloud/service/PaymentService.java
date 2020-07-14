package com.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String paymentInfo(Integer id){

        return "线程池：    " + Thread.currentThread().getName() + "paymentInfo_ok" + id;
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")})
    public String paymentInfo_Timeout(Integer id){
        int timeNumber = 4000;
        try {
            TimeUnit.MILLISECONDS.sleep(timeNumber);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "线程池：    " + Thread.currentThread().getName() + "Timeout" + id + "耗时" + timeNumber;
    }

    public String paymentInfo_TimeoutHandler(Integer id){
        return "线程池：    " + Thread.currentThread().getName() + "Handler" + id;
    }

    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            //请求次数
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            //时间窗口期
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            //失败率60=60%达到多次后跳闸
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if(id < 0){
            throw new RuntimeException("*****id不能为负数");
        }
        String serialNmber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + " 调用成功，流水号" + serialNmber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id不能为负数    " + id;
    }
}
