package com.liurk.springcloud.loadbalance.impl;

import com.liurk.springcloud.loadbalance.LoadBalance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 自定义负载均衡策略：轮询(使用)
 * @author: liurunkai
 * @Date: 2020/4/16 18:02
 */
@Slf4j
@Component
public class LoadBalanceImpl implements LoadBalance {

    AtomicInteger atomicInteger = new AtomicInteger(0);

    private final Integer getNext() {
        Integer current = null;
        Integer next = null;
        // 自旋锁+cas
        do {
            current = this.atomicInteger.get();
            next = current > Integer.MAX_VALUE ? 0 : current + 1;
        } while (!atomicInteger.compareAndSet(current,next));
        // 不能使用下面来自旋锁，因为第一次next为null，会报空指针
//        while (!atomicInteger.compareAndSet(current,next)) {
//            current = this.atomicInteger.get();
//            next = current > Integer.MAX_VALUE ? 0 : current + 1;
//        }
        log.info("第{}次请求",next);
        return next;
    }

    @Override
    public ServiceInstance getServiceInstance(List<ServiceInstance> serviceInstanceList) {
        Integer next = getNext() % serviceInstanceList.size();
        return serviceInstanceList.get(next);
    }
}
