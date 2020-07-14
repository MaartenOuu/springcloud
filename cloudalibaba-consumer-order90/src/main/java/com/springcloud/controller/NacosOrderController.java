package com.springcloud.controller;

import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class NacosOrderController {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${service-url.nacos-user-service}")
    private String serverUrl;

    @GetMapping("/consumer/payment/server")
    public String getServerPort(){
        return restTemplate.getForObject(serverUrl + "/payment/server",String.class);
    }

    @GetMapping("/payment/{id}")
    public CommonResult getPaymentById(@PathVariable("id")Long id){
        return restTemplate.getForObject(serverUrl + "/payment/" + id,CommonResult.class);
    }
}
