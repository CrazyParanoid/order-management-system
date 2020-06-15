package org.example.invoice.service.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.invoice.service.domain.Invoice;
import org.example.invoice.service.domain.InvoiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateInvoiceCommandProcessor implements CommandProcessor<CreateInvoiceCommand>{

    private final InvoiceRepository invoiceRepository;

    @Override
    @Transactional
    public void process(CreateInvoiceCommand command) {
        log.info("Create invoice");
        Invoice invoice = Invoice.create(command.getPaymentId(), command.getOrderId());
        invoiceRepository.save(invoice);
        log.info("Invoice has been saved");
    }

}
