package org.example.invoice.service.infrastructure.flow;

import io.zeebe.client.ZeebeClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.invoice.service.domain.DomainEventPublisher;
import org.example.invoice.service.domain.InvoiceId;
import org.example.invoice.service.domain.InvoiceIssued;
import org.example.invoice.service.domain.order.OrderId;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class InvoiceIssuedEventPublisher implements DomainEventPublisher<InvoiceIssued> {

    private final ZeebeClient zeebeClient;

    @Override
    public void publish(List<InvoiceIssued> domainEvents) {
        for(InvoiceIssued event: domainEvents){
            OrderId orderId = event.getOrderId();
            InvoiceId invoiceId = event.getInvoiceId();

            Map<String, Object> variables = new HashMap<>();
            variables.put("orderId", orderId.getId());
            variables.put("invoiceId", invoiceId.getId());

            zeebeClient.newPublishMessageCommand()
                    .messageName("PendingInvoice")
                    .correlationKey(orderId.getId())
                    .variables(variables)
                    .timeToLive(Duration.ofMinutes(30))
                    .send()
                    .join();
        }
    }

}
