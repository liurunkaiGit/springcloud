package com.liurk.springcloud.service.impl;

import com.liurk.springcloud.entry.CommonResult;
import com.liurk.springcloud.entry.Payment;
import com.liurk.springcloud.mapper.PaymentMapper;
import com.liurk.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/3/29 16:41
 */
@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public int create(Payment payment) {
        return this.paymentMapper.create(payment);
    }

    @Override
    public Payment selectPaymentById(Long id) {
        return this.paymentMapper.selectPaymentById(id);
    }

    @Override
    @HystrixCommand(fallbackMethod = "selectPaymentByIdRdFallback", commandProperties = {
            // 在10秒钟之内，10次请求中失败率达到60%(即失败超过6次)就开启断路器
            @HystrixProperty(name = "circuiBreaker.enabled",value = "true"), // 是否开启断路器
            @HystrixProperty(name = "circuiBreaker.requestVolumeThreshold",value = "10"), //请求次数
            @HystrixProperty(name = "circuiBreaker.sleepWindowInMilliseconds",value = "10000"), // 时间窗口期(时间范围)
            @HystrixProperty(name = "circuiBreaker.errorThresholdPercentage",value = "60"), // 失败率
    })
    public Payment selectPaymentByIdRd(Long id) {
        if (id < 0) {
            throw new RuntimeException("id不能为负数");
        }
        Payment payment = this.selectPaymentById(id);
        return payment;
    }

    public CommonResult<Payment> selectPaymentByIdRdFallback(Long id) {
        if (id < 0) {
            throw new RuntimeException("id不能为负数");
        }
        return new CommonResult(200, "成功", "id不能为负数");
    }
}
