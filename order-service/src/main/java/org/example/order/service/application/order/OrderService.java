package org.example.order.service.application.order;

public interface OrderService {

    OrderDTO createOrder(OrderDTO rawOrder);

    OrderDTO searchOrder(String id);

}
