package org.example.order.service.infrastructure.flow;

import io.zeebe.client.ZeebeClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.order.service.domain.DomainEventPublisher;
import org.example.order.service.domain.OrderCreated;
import org.example.order.service.domain.OrderId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderCreatedEventPublisher implements DomainEventPublisher<OrderCreated> {

    @Value("${flow.processId}")
    private String processId;

    private final ZeebeClient zeebeClient;

    @Override
    public void publish(List<OrderCreated> domainEvents) {
        for(OrderCreated event: domainEvents){
            OrderId orderId = event.getOrderId();

            Map<String, Object> variables = new HashMap<>();
            variables.put("orderId", orderId.getId());

            zeebeClient.newCreateInstanceCommand()
                    .bpmnProcessId(processId)
                    .latestVersion()
                    .variables(variables)
                    .send()
                    .join();
        }
    }

}
