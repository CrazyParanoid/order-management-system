package org.example.order.service.application.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.order.service.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final OrderAssembler orderAssembler;
    private final DomainEventPublisher<OrderCreated> orderCreatedEventPublisher;

    @Override
    @Transactional
    public OrderDTO createOrder(OrderDTO rawOrder) {
        log.info("Order creation");
        Order order = Order.create(rawOrder.getProductId(), rawOrder.getClientId());
        OrderId orderId = order.getOrderId();
        log.info("Order with id {} has been created", orderId.getId());

        orderRepository.save(order);
        log.info("Order with id {} has been saved", orderId.getId());
        orderCreatedEventPublisher.publish(order.getDomainEventsByType(OrderCreated.class));
        log.info("OrderCreated event has been published");

        return orderAssembler.writeDTO(order);
    }

    @Override
    public OrderDTO searchOrder(String id) {
        log.info("Search order");
        OrderId orderId = OrderId.identifyOrderFrom(id);
        Order order = orderRepository.orderOfId(orderId);
        log.info("Order has been found");

        return orderAssembler.writeDTO(order);
    }

}
