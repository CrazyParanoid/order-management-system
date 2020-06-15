package org.example.payment.service.application;

import org.example.payment.service.domain.Amount;
import org.example.payment.service.domain.order.OrderId;
import org.example.payment.service.domain.Payment;
import org.example.payment.service.domain.PaymentId;
import org.springframework.stereotype.Service;

@Service
public class PaymentAssembler {

    PaymentDTO writeDTO(Payment payment){
        PaymentId paymentId = payment.getPaymentId();
        OrderId orderId = payment.getOrderId();
        Amount amount = payment.getAmount();

        return new PaymentDTO(paymentId.getId(),
                orderId.getId(),
                amount.getValue());
    }

}
