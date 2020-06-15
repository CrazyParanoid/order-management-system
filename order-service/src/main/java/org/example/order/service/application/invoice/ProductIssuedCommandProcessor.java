package org.example.order.service.application.invoice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.order.service.application.CommandProcessor;
import org.example.order.service.domain.Order;
import org.example.order.service.domain.OrderId;
import org.example.order.service.domain.OrderRepository;
import org.example.order.service.domain.invoice.InvoiceId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductIssuedCommandProcessor implements CommandProcessor<ProductIssuedCommand> {

    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public void process(ProductIssuedCommand command) {
        log.info("Issue product with id {}", command.getInvoiceId());
        OrderId orderId = OrderId.identifyOrderFrom(command.getOrderId());
        InvoiceId invoiceId = InvoiceId.identifyInvoiceFrom(command.getInvoiceId());

        Order order = orderRepository.orderOfId(orderId);
        order.issueProduct(invoiceId);
        log.info("Product has been issued for order with id {}", orderId.getId());

        orderRepository.save(order);
        log.info("Order with id {} has been saved", orderId.getId());
    }

}
