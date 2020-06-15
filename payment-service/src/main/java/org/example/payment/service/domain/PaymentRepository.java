package org.example.payment.service.domain;

public interface PaymentRepository {

    Payment paymentOfId(PaymentId paymentId);

    void save(Payment payment);

}
