package org.example.payment.service.application;

public interface PaymentService {

    PaymentDTO makePayment(PaymentDTO rawPayment);

    PaymentDTO searchPayment(String id);

}
