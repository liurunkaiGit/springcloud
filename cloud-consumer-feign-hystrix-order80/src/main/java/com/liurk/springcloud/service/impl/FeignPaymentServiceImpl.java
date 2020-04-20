package com.liurk.springcloud.service.impl;

import com.liurk.springcloud.entry.CommonResult;
import com.liurk.springcloud.entry.Payment;
import com.liurk.springcloud.service.FeignPaymentService;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/4/18 18:58
 */
@Service
public class FeignPaymentServiceImpl implements FeignPaymentService {
    @Override
    public CommonResult<Payment> selectPaymentById(Long id) {
        Payment payment = Payment.builder().id(-1L).serial("80请求异常，请稍后重试").build();
        return new CommonResult(500, "FeignPaymentServiceImpl失败:异常", payment);
    }

    @Override
    public CommonResult<Payment> selectPaymentByIdTimeout(Long id) {
        Payment payment = Payment.builder().id(-1L).serial("80请求超时，请稍后重试").build();
        return new CommonResult(500, "FeignPaymentServiceImpl失败：超时", payment);
    }
}
