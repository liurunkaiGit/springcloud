package com.liurk.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/4/16 21:28
 */
@EnableFeignClients
@SpringBootApplication
public class OrderFeignApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderFeignApplication.class, args);
    }
}
