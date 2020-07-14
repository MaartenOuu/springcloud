package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class StreamRabbitMain8801 {

    public static void main(String[] args){
        SpringApplication.run(StreamRabbitMain8801.class, args);
    }
}
