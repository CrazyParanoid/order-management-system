package org.example.order.service.domain;

public interface OrderRepository {

    Order orderOfId(OrderId orderId);

    void save(Order order);

}
