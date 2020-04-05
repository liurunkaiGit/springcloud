package com.liurk.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/4/5 21:51
 */
@EnableDiscoveryClient
@SpringBootApplication
public class OrderConsulApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderConsulApplication.class, args);
    }
}
