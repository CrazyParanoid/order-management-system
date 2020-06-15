package org.example.invoice.service.infrastructure.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.invoice.service.domain.Invoice;
import org.example.invoice.service.domain.InvoiceId;
import org.example.invoice.service.domain.InvoiceRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class InvoiceRepositoryImpl implements InvoiceRepository {

    private final InvoiceJpaRepository invoiceJpaRepository;

    @Override
    public Invoice invoiceOfId(InvoiceId invoiceId) {
        try{
            return invoiceJpaRepository.findByInvoiceId(invoiceId)
                    .orElseThrow(() -> new InvoiceNotFoundException(String.format("Invoice with id %s is not found", invoiceId.getId())));
        } catch (DataAccessException e){
            log.error(e.getMessage(), e);
            throw new RepositoryAccessException(e.getMessage(), e);
        }
    }

    @Override
    public void save(Invoice invoice) {
        try{
            invoiceJpaRepository.save(invoice);
        } catch (DataAccessException e){
            log.error(e.getMessage(), e);
            throw new RepositoryAccessException(e.getMessage(), e);
        }
    }

}
