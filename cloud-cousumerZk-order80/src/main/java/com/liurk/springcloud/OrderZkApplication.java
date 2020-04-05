package com.liurk.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/4/5 19:50
 */
@EnableDiscoveryClient
@SpringBootApplication
public class OrderZkApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderZkApplication.class, args);
    }
}
