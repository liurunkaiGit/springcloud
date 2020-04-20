package com.liurk.springcloud.service;

import com.liurk.springcloud.entry.Payment;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/3/29 16:40
 */
public interface PaymentService {
    int create(Payment payment);

    Payment selectPaymentById(Long id);

    Payment selectPaymentByIdRd(Long id);
}
