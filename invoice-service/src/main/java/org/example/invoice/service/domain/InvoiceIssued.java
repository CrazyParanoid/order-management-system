package org.example.invoice.service.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.invoice.service.domain.order.OrderId;
import org.example.invoice.service.domain.supertype.DomainEvent;

@Data
@AllArgsConstructor
public class InvoiceIssued implements DomainEvent {

    private OrderId orderId;
    private InvoiceId invoiceId;

}
