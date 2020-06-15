package org.example.payment.service.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.payment.service.domain.order.OrderId;
import org.example.payment.service.domain.supertype.DomainEvent;

@Data
@AllArgsConstructor
public class PaymentCompleted implements DomainEvent {

    private OrderId orderId;
    private PaymentId paymentId;

}
