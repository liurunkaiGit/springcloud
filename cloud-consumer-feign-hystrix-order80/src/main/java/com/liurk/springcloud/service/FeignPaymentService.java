package com.liurk.springcloud.service;

import com.liurk.springcloud.entry.CommonResult;
import com.liurk.springcloud.entry.Payment;
import com.liurk.springcloud.service.impl.FeignPaymentServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/4/16 21:32
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-HYSTRIX-SERVICE", fallback = FeignPaymentServiceImpl.class)
public interface FeignPaymentService {

    @GetMapping(value = "/paymentService/payment/selectPaymentById")
    CommonResult<Payment> selectPaymentById(@RequestParam("id") Long id);

    @GetMapping(value = "/paymentService/payment/selectPaymentById/timeout")
    CommonResult<Payment> selectPaymentByIdTimeout(@RequestParam("id") Long id);

}
