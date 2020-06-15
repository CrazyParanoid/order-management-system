package org.example.payment.service.infrastructure.flow;

import io.zeebe.client.ZeebeClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.payment.service.domain.DomainEventPublisher;
import org.example.payment.service.domain.order.OrderId;
import org.example.payment.service.domain.PaymentCompleted;
import org.example.payment.service.domain.PaymentId;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentCompletedEventPublisher implements DomainEventPublisher<PaymentCompleted> {

    private final ZeebeClient zeebeClient;

    @Override
    public void publish(List<PaymentCompleted> domainEvents) {
        for(PaymentCompleted event: domainEvents){
            OrderId orderId = event.getOrderId();
            PaymentId paymentId = event.getPaymentId();

            Map<String, Object> variables = new HashMap<>();
            variables.put("orderId", orderId.getId());
            variables.put("paymentId", paymentId.getId());

            zeebeClient.newPublishMessageCommand()
                    .messageName("PendingPayment")
                    .correlationKey(orderId.getId())
                    .variables(variables)
                    .timeToLive(Duration.ofMinutes(30))
                    .send()
                    .join();
        }
    }

}
