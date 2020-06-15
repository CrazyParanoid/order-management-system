package org.example.order.service.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.order.service.domain.supertype.DomainEvent;

@Data
@AllArgsConstructor
public class OrderCreated implements DomainEvent {

    private OrderId orderId;

}
