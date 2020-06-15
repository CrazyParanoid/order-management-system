package org.example.order.service.application.order;

import org.example.order.service.domain.Order;
import org.example.order.service.domain.OrderId;
import org.example.order.service.domain.client.ClientId;
import org.example.order.service.domain.payment.PaymentId;
import org.example.order.service.domain.product.ProductId;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrderAssembler {

    OrderDTO writeDTO(Order order){
        OrderId orderId = order.getOrderId();
        PaymentId paymentId = order.getPaymentId();
        ProductId productId = order.getProductId();
        ClientId clientId = order.getClientId();
        Order.Status status = order.getStatus();

        OrderDTO orderDTO = new OrderDTO(orderId.getId(), clientId.getId(),
                productId.getId(), status.getValue());

        Optional.ofNullable(paymentId).ifPresent(p -> orderDTO.setPaymentId(p.getId()));

        return orderDTO;
    }

}
