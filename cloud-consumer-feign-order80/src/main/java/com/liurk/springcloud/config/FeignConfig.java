package com.liurk.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/4/17 13:03
 */
@Configuration
public class FeignConfig {

    /**
     * feign日志级别配置，默认为NONE，不打印日志
     * @return
     */
    @Bean
    Logger.Level feignLoggerLever() {
        return Logger.Level.FULL;
    }
}
