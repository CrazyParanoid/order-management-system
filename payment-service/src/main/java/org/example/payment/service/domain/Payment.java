package org.example.payment.service.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.payment.service.domain.order.OrderId;
import org.example.payment.service.domain.supertype.AggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name = "payments")
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Payment extends AggregateRoot {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="id", column=@Column(name="payment_id"))
    })
    private PaymentId paymentId;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="id", column=@Column(name="order_id"))
    })
    private OrderId orderId;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="value", column=@Column(name="amount"))
    })
    private Amount amount;

    public static Payment make(Double amountValue, String rawOrderId){
        Amount amount = new Amount(amountValue);
        PaymentId paymentId = PaymentId.identifyPayment();
        OrderId orderId = OrderId.identifyOrderFrom(rawOrderId);

        Payment payment = new Payment(paymentId,
                orderId,
                amount);
        payment.registerDomainEvent(new PaymentCompleted(payment.getOrderId(), payment.getPaymentId()));

        return payment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o)) return false;
        Payment payment = (Payment) o;
        return paymentId.equals(payment.paymentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), paymentId);
    }

}
