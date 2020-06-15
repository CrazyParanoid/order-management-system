package org.example.order.service.domain;

import lombok.*;
import org.example.order.service.domain.client.ClientId;
import org.example.order.service.domain.invoice.InvoiceId;
import org.example.order.service.domain.payment.PaymentId;
import org.example.order.service.domain.product.ProductId;
import org.example.order.service.domain.supertype.AggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Order extends AggregateRoot {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="id", column=@Column(name="order_id"))
    })
    private OrderId orderId;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="id", column=@Column(name="product_id"))
    })
    private ProductId productId;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="id", column=@Column(name="client_id"))
    })
    private ClientId clientId;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="id", column=@Column(name="payment_id"))
    })
    private PaymentId paymentId;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="id", column=@Column(name="invoice_id"))
    })
    private InvoiceId invoiceId;
    @Enumerated(EnumType.STRING)
    private Status status;

    private Order(OrderId orderId,
                  ProductId productId,
                  ClientId clientId,
                  Status status) {
        this.orderId = orderId;
        this.productId = productId;
        this.clientId = clientId;
        this.status = status;
    }

    public void acceptPayment(PaymentId paymentId){
        this.paymentId = paymentId;
        this.status = Status.PENDING_PRODUCT_ISSUE;
    }

    public void issueProduct(InvoiceId invoiceId){
        this.invoiceId = invoiceId;
        this.status = Status.PRODUCT_ISSUED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;

        Order order = (Order) o;
        return orderId.equals(order.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), orderId);
    }

    public static Order create(String rawProductId, String rawClientId){
        ProductId productId = ProductId.identifyProductFrom(rawProductId);
        ClientId clientId = ClientId.identifyClientFrom(rawClientId);
        OrderId orderId = OrderId.identifyOrder();

        Order order = new Order(orderId, productId,
                clientId, Status.PENDING_PAYMENT);
        order.registerDomainEvent(new OrderCreated(order.getOrderId()));

        return order;
    }

    @RequiredArgsConstructor
    public enum Status{
        PENDING_PAYMENT("PENDING_PAYMENT"),
        PENDING_PRODUCT_ISSUE("PENDING_PRODUCT_ISSUE"),
        PRODUCT_ISSUED("PENDING_PAYMENT");

        @Getter
        private final String value;
    }

}
