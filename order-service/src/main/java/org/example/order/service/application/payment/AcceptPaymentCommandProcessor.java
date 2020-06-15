package org.example.order.service.application.payment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.order.service.application.CommandProcessor;
import org.example.order.service.domain.Order;
import org.example.order.service.domain.OrderId;
import org.example.order.service.domain.OrderRepository;
import org.example.order.service.domain.payment.PaymentId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AcceptPaymentCommandProcessor implements CommandProcessor<AcceptPaymentCommand> {

    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public void process(AcceptPaymentCommand command) {
        log.info("Accept payment with id {}", command.getPaymentId());
        OrderId orderId = OrderId.identifyOrderFrom(command.getOrderId());
        PaymentId paymentId = PaymentId.identifyPaymentFrom(command.getPaymentId());

        Order order = orderRepository.orderOfId(orderId);
        order.acceptPayment(paymentId);
        log.info("Payment has been accepted for order with id {}", orderId.getId());

        orderRepository.save(order);
        log.info("Order with id {} has been saved", orderId.getId());
    }

}
