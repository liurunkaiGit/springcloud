package com.liurk.springcloud.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/3/29 18:13
 */
@Configuration // 里面包含了@Component注解
public class ApplicationContextConfig {

    // 相当于<bean id= "" name = "">
    @Bean
    @LoadBalanced // 开启负载均衡，默认轮询
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
//    RestOperations restTemplate(RestTemplateBuilder builder) {
//        return builder.build();
//    }
}
