package com.springcloud.controller;

import com.springcloud.service.IMessagrProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessageController {

    @Autowired
    private IMessagrProvider iMessagrProvider;

    @GetMapping("/sendMessage")
    public String sendMessage(){
        return iMessagrProvider.send();
    }
}
