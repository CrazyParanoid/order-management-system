package org.example.invoice.service;

import lombok.extern.slf4j.Slf4j;
import org.example.invoice.service.domain.Invoice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.example.invoice.service.domain.Invoice.Status.ISSUED;
import static org.example.invoice.service.domain.Invoice.Status.PENDING_ISSUE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Slf4j
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {Application.class})
public class InvoiceTest {

    private Invoice createInvoice(){
        String paymentId = UUID.randomUUID().toString();
        String orderId = UUID.randomUUID().toString();

        return Invoice.create(paymentId, orderId);
    }

    @Test
    public void testCreateInvoice(){
        Invoice invoice = createInvoice();

        assertNotNull(invoice.getOrderId());
        assertNotNull(invoice.getPaymentId());
        assertNotNull(invoice.getInvoiceId());
        assertEquals(invoice.getStatus(), PENDING_ISSUE);
    }

    @Test
    public void testIssueInvoice(){
        Invoice invoice = createInvoice();

        invoice.issueToBuyer();;

        assertNotNull(invoice.getOrderId());
        assertNotNull(invoice.getPaymentId());
        assertNotNull(invoice.getInvoiceId());
        assertNotNull(invoice.getIssueDate());
        assertEquals(invoice.getStatus(), ISSUED);
    }

}
