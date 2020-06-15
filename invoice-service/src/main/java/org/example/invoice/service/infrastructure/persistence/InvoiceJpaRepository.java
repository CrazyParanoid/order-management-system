package org.example.invoice.service.infrastructure.persistence;

import org.example.invoice.service.domain.Invoice;
import org.example.invoice.service.domain.InvoiceId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvoiceJpaRepository extends JpaRepository<Invoice, Long> {

    Optional<Invoice> findByInvoiceId(InvoiceId invoiceId);

}
