package org.example.invoice.service.input.ports.flow;

import io.zeebe.client.api.response.ActivatedJob;
import io.zeebe.client.api.worker.JobClient;
import io.zeebe.spring.client.annotation.ZeebeWorker;
import lombok.RequiredArgsConstructor;
import org.example.invoice.service.application.CommandProcessor;
import org.example.invoice.service.application.CreateInvoiceCommand;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CreateInvoiceFlowWorker implements FlowWorker{

    private static final String ORDER_ID = "orderId";
    private static final String PAYMENT_ID = "paymentId";

    private final CommandProcessor<CreateInvoiceCommand> createInvoiceCommandProcessor;

    @Override
    @ZeebeWorker(type = "create-invoice", name = "create-invoice-worker")
    public void executeJob(JobClient client, ActivatedJob job) {
        Map<String, Object> variables = job.getVariablesAsMap();
        String paymentId = (String) variables.get(PAYMENT_ID);
        String orderId = (String) variables.get(ORDER_ID);

        CreateInvoiceCommand command = new CreateInvoiceCommand(orderId, paymentId);
        createInvoiceCommandProcessor.process(command);

        Map<String, Object> outVariables = new HashMap<>();
        variables.put(ORDER_ID, orderId);

        client.newCompleteCommand(job.getKey())
                .variables(outVariables)
                .send()
                .join();
    }

}
