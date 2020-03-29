package com.liurk.springcloud.controller;

import com.liurk.springcloud.entry.CommonResult;
import com.liurk.springcloud.entry.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/3/29 18:10
 */
@Slf4j
@RestController
@RequestMapping("/order/consumer")
public class OrderController {

    private static final String PAYMENT_URL = "http://localhost:8001/paymentService/";

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping(value = "/createPayment", consumes = "application/json")
    public CommonResult<Payment> create(@RequestBody Payment payment) {
        ResponseEntity<CommonResult> commonResultResponseEntity = restTemplate.postForEntity(PAYMENT_URL + "payment/createPayment", payment, CommonResult.class);
        return commonResultResponseEntity.getBody();
    }

    @GetMapping(value = "/selectPaymentById")
    public CommonResult<Payment> selectPaymentById(Long id) {
        CommonResult commonResult = restTemplate.getForObject(PAYMENT_URL + "payment/selectPaymentById?id=" + id, CommonResult.class);
        return commonResult;

    }
}
