package com.liurk.springcloud.controller;

import com.liurk.springcloud.entry.CommonResult;
import com.liurk.springcloud.entry.Payment;
import com.liurk.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/selectPaymentById")
    public CommonResult<Payment> selectPaymentById(Long id) {
        Payment payment = this.paymentService.selectPaymentById(id);
        log.info("payment is {},port is {}", payment, port);
        return new CommonResult(200, "成功", payment);
    }

    @PostMapping(value = "/createPayment", consumes = "application/json")
    public CommonResult create(@RequestBody Payment payment) {
        this.paymentService.create(payment);
        log.info("插入成功port is {}", port);
        return new CommonResult(200, "成功");
    }
}
