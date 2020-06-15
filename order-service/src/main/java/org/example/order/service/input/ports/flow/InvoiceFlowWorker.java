package org.example.order.service.input.ports.flow;

import io.zeebe.client.api.response.ActivatedJob;
import io.zeebe.client.api.worker.JobClient;
import io.zeebe.spring.client.annotation.ZeebeWorker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.order.service.application.CommandProcessor;
import org.example.order.service.application.invoice.ProductIssuedCommand;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class InvoiceFlowWorker implements FlowWorker {

    private static final String ORDER_ID = "orderId";
    private static final String INVOICE_ID = "invoiceId";

    private final CommandProcessor<ProductIssuedCommand> productIssuedCommandProcessor;

    @Override
    @ZeebeWorker(type = "issue-product", name = "issue-product-worker")
    public void executeJob(JobClient client, ActivatedJob job) {
        Map<String, Object> variables = job.getVariablesAsMap();
        String invoiceId = (String) variables.get(INVOICE_ID);
        String orderId = (String) variables.get(ORDER_ID);

        ProductIssuedCommand command = new ProductIssuedCommand(orderId, invoiceId);
        productIssuedCommandProcessor.process(command);

        Map<String, Object> outVariables = new HashMap<>();
        variables.put(ORDER_ID, orderId);

        client.newCompleteCommand(job.getKey())
                .variables(outVariables)
                .send()
                .join();
    }

}
