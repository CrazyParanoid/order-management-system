package org.example.invoice.service.domain;

public interface InvoiceRepository {

    Invoice invoiceOfId(InvoiceId invoiceId);

    void save (Invoice invoice);

}
