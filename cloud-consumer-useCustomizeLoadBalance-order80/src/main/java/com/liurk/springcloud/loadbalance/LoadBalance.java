package com.liurk.springcloud.loadbalance;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/4/16 18:00
 */
public interface LoadBalance {

    ServiceInstance getServiceInstance(List<ServiceInstance> serviceInstanceList);
}
