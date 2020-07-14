package com.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(value = "nacos-provide")
public interface FeignService {

    @GetMapping("/payment/server")
    public String getServerPort();
}
