package org.example.order.service.input.ports.flow;

import io.zeebe.client.api.response.ActivatedJob;
import io.zeebe.client.api.worker.JobClient;
import io.zeebe.spring.client.annotation.ZeebeWorker;
import lombok.RequiredArgsConstructor;
import org.example.order.service.application.CommandProcessor;
import org.example.order.service.application.payment.AcceptPaymentCommand;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class PaymentFlowWorker implements FlowWorker {

    private static final String ORDER_ID = "orderId";
    private static final String PAYMENT_ID = "paymentId";

    private final CommandProcessor<AcceptPaymentCommand> acceptPaymentCommandProcessor;

    @Override
    @ZeebeWorker(type = "accept-payment", name = "accept-payment-worker")
    public void executeJob(JobClient client, ActivatedJob job) {
        Map<String, Object> variables = job.getVariablesAsMap();
        String paymentId = (String) variables.get(PAYMENT_ID);
        String orderId = (String) variables.get(ORDER_ID);

        AcceptPaymentCommand command = new AcceptPaymentCommand(orderId, paymentId);
        acceptPaymentCommandProcessor.process(command);

        Map<String, Object> outVariables = new HashMap<>();
        variables.put(ORDER_ID, orderId);

        client.newCompleteCommand(job.getKey())
                .variables(outVariables)
                .send()
                .join();
    }

}
