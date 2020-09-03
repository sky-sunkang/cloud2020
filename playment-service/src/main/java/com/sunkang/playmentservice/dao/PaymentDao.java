package com.sunkang.playmentservice.dao;

import com.sunkang.apiCommon.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {

    int create(Payment payment);

    Payment getPaymentById(@Param(value = "id") Long id);
}
