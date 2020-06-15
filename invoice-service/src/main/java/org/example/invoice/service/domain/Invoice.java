package org.example.invoice.service.domain;

import lombok.*;
import org.example.invoice.service.domain.order.OrderId;
import org.example.invoice.service.domain.payment.PaymentId;
import org.example.invoice.service.domain.supertype.AggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;


@Data
@Entity
@Table(name = "invoices")
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Invoice extends AggregateRoot {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="id", column=@Column(name="invoice_id"))
    })
    private InvoiceId invoiceId;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="id", column=@Column(name="order_id"))
    })
    private OrderId orderId;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="id", column=@Column(name="payment_id"))
    })
    private PaymentId paymentId;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Date issueDate;

    private Invoice(InvoiceId invoiceId,
                    OrderId orderId,
                    PaymentId paymentId,
                    Status status) {
        this.invoiceId = invoiceId;
        this.orderId = orderId;
        this.paymentId = paymentId;
        this.status = status;
    }

    public void issueToBuyer(){
        this.issueDate = new Date();
        this.status = Status.ISSUED;

        registerDomainEvent(new InvoiceIssued(this.orderId,
                this.invoiceId));
    }

    public static Invoice create(String rawPaymentId, String rawOrderId){
        PaymentId paymentId = PaymentId.identifyPaymentFrom(rawPaymentId);
        OrderId orderId = OrderId.identifyOrderFrom(rawOrderId);
        InvoiceId invoiceId = InvoiceId.identifyInvoice();

        return new Invoice(invoiceId,
                orderId,
                paymentId,
                Status.PENDING_ISSUE);
    }

    @RequiredArgsConstructor
    public enum Status{
        PENDING_ISSUE("PENDING_ISSUE"),
        ISSUED("ISSUED");

        @Getter
        private final String value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Invoice invoice = (Invoice) o;
        return Objects.equals(invoiceId, invoice.invoiceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), invoiceId);
    }

}
