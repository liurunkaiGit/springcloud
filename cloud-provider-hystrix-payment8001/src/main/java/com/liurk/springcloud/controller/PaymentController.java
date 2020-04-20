package com.liurk.springcloud.controller;

import com.liurk.springcloud.entry.CommonResult;
import com.liurk.springcloud.entry.Payment;
import com.liurk.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/3/29 16:44
 */
@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Value("${server.port}")
    private String port;

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private DiscoveryClient discoveryClient;

    // ==================服务降级
    @GetMapping(value = "/selectPaymentById")
    public CommonResult<Payment> selectPaymentById(Long id) {
        Payment payment = this.paymentService.selectPaymentById(id);
        log.info("payment is {},port is {}", payment, port);
        return new CommonResult(200, "成功", payment);
    }

    /**
     * 服务生产者自身服务降级处理：
     * 设置当前方法处理时间为3秒钟：@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")，
     * 如果超出3秒钟，则调用服务降级的方法：@HystrixCommand(fallbackMethod = "selectPaymentByIdTimeoutHandler"
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/selectPaymentById/timeout")
    @HystrixCommand(fallbackMethod = "selectPaymentByIdTimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public CommonResult<Payment> selectPaymentByIdTimeout(Long id) {
        Payment payment = this.paymentService.selectPaymentById(id);
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        int i = 10 / 0;
        log.info("payment is {},port is {}", payment, port);
        return new CommonResult(200, "成功", payment);
    }

    /**
     * 服务降级回调方法
     *
     * @param id
     * @return
     */
    public CommonResult<Payment> selectPaymentByIdTimeoutHandler(Long id) {
        Payment payment = Payment.builder().id(id).serial("请求超时，请稍后重试").build();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("payment is {},port is {}", payment, port);
        return new CommonResult(200, "成功", payment);
    }

    @PostMapping(value = "/createPayment", consumes = "application/json")
    public CommonResult create(@RequestBody Payment payment) {
        this.paymentService.create(payment);
        log.info("插入成功port is {}", port);
        return new CommonResult(200, "成功");
    }

    @GetMapping(value = "/payment/discovery")
    public Object paymentDiscovery() {
        List<String> servicesList = discoveryClient.getServices();
        servicesList.forEach(service -> {
            log.info("service is {}", service);
        });
        List<ServiceInstance> instanceList = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        instanceList.forEach(instance -> {
            log.info("instanceId is {},host is {},port is {},uri is {}", instance.getInstanceId(), instance.getHost(), instance.getPort(), instance.getUri());
        });
        return this.discoveryClient;
    }

    // =====================服务熔断
    @GetMapping(value = "/selectPaymentByIdRd")
    public CommonResult<Payment> selectPaymentByIdRd(Long id) {
        Payment payment = this.paymentService.selectPaymentByIdRd(id);
        log.info("payment is {},port is {}", payment, port);
        return new CommonResult(200, "成功", payment);
    }
}
