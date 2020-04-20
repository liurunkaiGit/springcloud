package com.liurk.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/4/16 21:28
 */
@EnableHystrix
@EnableFeignClients
@SpringBootApplication
public class OrderFeignHystrixApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderFeignHystrixApplication.class, args);
    }
}
