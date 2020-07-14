package com.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;
import com.springcloud.myhandler.BlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handlerException")
    public CommonResult byResource(){
        return new CommonResult(200, "按自愿名称限流测试ok", new Payment(2020L, "serial001"));
    }
    public CommonResult handlerException(BlockException exception){
        return new CommonResult(444,exception.getClass().getCanonicalName()+"\t 服务不可用");
    }

    @GetMapping("/BlockHandler")
    @SentinelResource(value = "BlockHandler",
            blockHandlerClass = BlockHandler.class,
            blockHandler = "handlerException2")
    public CommonResult BlockHandler(){
        return new CommonResult(200, "按客户自定义测试ok", new Payment(2020L, "serial001"));
    }
}
