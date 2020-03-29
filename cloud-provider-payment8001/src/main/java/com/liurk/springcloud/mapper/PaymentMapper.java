package com.liurk.springcloud.mapper;

import com.liurk.springcloud.entry.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2020/3/29 16:26
 */
// 用@Repository 这个注解插入的时候可能会有问题，这里推荐使用@Mapper注解
@Mapper
public interface PaymentMapper {

    int create(Payment payment);

    Payment selectPaymentById(@Param("id") Long id);
}
