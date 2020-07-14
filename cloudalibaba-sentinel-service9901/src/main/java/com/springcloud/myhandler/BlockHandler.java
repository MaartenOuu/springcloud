package com.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.springcloud.entities.CommonResult;

public class BlockHandler {

    public static CommonResult handlerException(BlockException exception){
        return new CommonResult(444, "按客户自定义测试ok");

    }

    public static CommonResult handlerException2(BlockException exception){
        return new CommonResult(444, "按客户自定义测试ok");

    }
}
