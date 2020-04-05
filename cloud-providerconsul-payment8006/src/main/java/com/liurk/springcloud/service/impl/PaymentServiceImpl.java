package com.liurk.springcloud.service.impl;

import com.liurk.springcloud.entry.Payment;
import com.liurk.springcloud.mapper.PaymentMapper;
import com.liurk.springcloud.service.PaymentService;
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
}
