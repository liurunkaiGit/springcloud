package com.liurk.springcloud.controller;

import com.liurk.springcloud.entry.CommonResult;
import com.liurk.springcloud.entry.Payment;
import com.liurk.springcloud.service.FeignPaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/4/16 21:37
 */
@Slf4j
@RestController
@RequestMapping("/order/consumer")
@DefaultProperties(defaultFallback = "GlobalFallback")
public class FeignOrderController {

    @Autowired
    private FeignPaymentService feignPaymentService;

    @GetMapping(value = "/selectPaymentById")
    public CommonResult<Payment> selectPaymentById(Long id) {
        CommonResult<Payment> commonResult = this.feignPaymentService.selectPaymentById(id);
        return commonResult;
    }

    @GetMapping(value = "/selectPaymentById/timeout")
//    @HystrixCommand(fallbackMethod = "selectPaymentByIdTimeoutHandler", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
//    })
    @HystrixCommand // 说明使用全局服务降级方法
    public CommonResult<Payment> selectPaymentByIdTimeout(Long id) {
        CommonResult<Payment> commonResult = this.feignPaymentService.selectPaymentByIdTimeout(id);
        int i = 10 / 0;
        return commonResult;
    }

    /**
     * 服务降级回调方法
     *
     * @param id
     * @return
     */
    public CommonResult<Payment> selectPaymentByIdTimeoutHandler(Long id) {
        Payment payment = Payment.builder().id(id).serial("80请求超时，请稍后重试").build();
        return new CommonResult(500, "失败", payment);
    }

    /**
     * 全局服务降级回调方法
     *
     * @return
     */
    public CommonResult<Payment> GlobalFallback() {
        Payment payment = Payment.builder().id(-1L).serial("80请求超时，请稍后重试").build();
        return new CommonResult(500, "失败", payment);
    }
}
