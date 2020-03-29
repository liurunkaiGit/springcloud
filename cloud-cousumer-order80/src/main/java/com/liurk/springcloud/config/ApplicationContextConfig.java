package com.liurk.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/3/29 18:13
 */
@Component
@Configuration
public class ApplicationContextConfig {

    // 相当于<bean id= "" name = "">
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
