package com.re.session14_bai1.service;

import com.re.session14_bai1.model.Order;
import com.re.session14_bai1.model.Wallet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void processPayment(Long orderId, Long walletId, double totalAmount) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            Order order = session.find(Order.class, orderId);
            order.setStatus("PAID");
            session.merge(order);

            // Giả lập lỗi hệ thống bất ngờ (Ví dụ: Mất kết nối đến Service Ví)
            if (true) throw new RuntimeException("Kết nối đến cổng thanh toán thất bại!");

            // 2. Trừ tiền trong ví khách hàng
            Wallet wallet = session.find(Wallet.class, walletId);
            wallet.setBalance(wallet.getBalance() - totalAmount);
            session.merge(wallet);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

}
