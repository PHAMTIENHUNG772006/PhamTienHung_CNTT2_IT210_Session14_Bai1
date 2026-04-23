package com.re.session14_bai1.service;

public interface PaymentService {
    void processPayment(Long orderId, Long walletId, double totalAmount);
}
