package com.liurk.springcloud.controller;

import com.liurk.springcloud.entry.CommonResult;
import com.liurk.springcloud.entry.Payment;
import com.liurk.springcloud.loadbalance.LoadBalance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/3/29 18:10
 */
@Slf4j
@RestController
@RequestMapping("/order/consumer")
public class OrderController {

    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalance loadBalance;

    /**
     * 使用自己写的负载均衡策略：轮询
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/selectPaymentById/customizeLoadBalance")
    public CommonResult<Payment> selectPaymentByIdCustomizeLoadBalance(Long id) {
        List<ServiceInstance> serviceInstanceList = this.discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (serviceInstanceList == null || serviceInstanceList.size() <= 0) {
            return null;
        }
        ServiceInstance serviceInstance = this.loadBalance.getServiceInstance(serviceInstanceList);
        log.info("url is {}", serviceInstance.getUri());
        CommonResult commonResult = restTemplate.getForObject(serviceInstance.getUri() + "/paymentService/payment/selectPaymentById?id=" + id, CommonResult.class);
        return commonResult;
    }
}
