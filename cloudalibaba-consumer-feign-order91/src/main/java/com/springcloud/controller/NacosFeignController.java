package com.springcloud.controller;

import com.springcloud.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NacosFeignController {

    @Autowired
    private FeignService feignService;

    @GetMapping("/consumer/payment/server")
    public String getServerPort(){
        return feignService.getServerPort();
    }
}
