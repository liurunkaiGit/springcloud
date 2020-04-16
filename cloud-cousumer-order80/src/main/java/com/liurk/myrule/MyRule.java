package com.liurk.myrule;

import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/4/15 12:59
 */
@Configuration
public class MyRule {

    @Bean
    public RandomRule randomRule() {
        return new RandomRule();
    }
}
