package org.example.invoice.service.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.invoice.service.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class InvoiceIssueCommandProcessor implements CommandProcessor<InvoiceIssueCommand>{

    private final InvoiceRepository invoiceRepository;
    private final DomainEventPublisher<InvoiceIssued> invoiceIssuedEventPublisher;

    @Override
    @Transactional
    public void process(InvoiceIssueCommand command) {
        log.info("Issue invoice with id {}", command.getInvoiceId());
        InvoiceId invoiceId = InvoiceId.identifyInvoiceFrom(command.getInvoiceId());
        Invoice invoice = invoiceRepository.invoiceOfId(invoiceId);

        invoice.issueToBuyer();
        invoiceIssuedEventPublisher.publish(invoice.getDomainEventsByType(InvoiceIssued.class));
        log.info("Issue with id {} has issued", invoiceId.getId());

        invoiceRepository.save(invoice);
        log.info("Issue with id {} has been saved", invoiceId.getId());
    }

}
