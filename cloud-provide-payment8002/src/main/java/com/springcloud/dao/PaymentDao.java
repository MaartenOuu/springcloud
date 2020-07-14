package com.springcloud.dao;

import com.springcloud.entities.Payment;


public interface PaymentDao {

     int create(Payment payment);

     Payment getPaymentById(Long id);
}
