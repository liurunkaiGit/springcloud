package com.liurk.springcloud.controller;

import com.liurk.springcloud.entry.CommonResult;
import com.liurk.springcloud.entry.Payment;
import com.liurk.springcloud.service.FeignPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/4/16 21:37
 */
@Slf4j
@RestController
@RequestMapping("/order/consumer")
public class FeignOrderController {

    @Autowired
    private FeignPaymentService feignPaymentService;

    @GetMapping(value = "/selectPaymentById")
    public CommonResult<Payment> selectPaymentById(Long id) {
        CommonResult<Payment> commonResult = this.feignPaymentService.selectPaymentById(id);
        return commonResult;
    }
}
