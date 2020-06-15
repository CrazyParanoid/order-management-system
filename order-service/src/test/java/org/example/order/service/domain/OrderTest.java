package org.example.order.service.domain;

import lombok.extern.slf4j.Slf4j;
import org.example.order.service.Application;
import org.example.order.service.domain.invoice.InvoiceId;
import org.example.order.service.domain.payment.PaymentId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.example.order.service.domain.Order.Status.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Slf4j
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {Application.class})
public class OrderTest {

    @Test
    public void testCreateOrder(){
        Order order = createOrder();

        assertNotNull(order.getClientId());
        assertNotNull(order.getProductId());
        assertNotNull(order.getOrderId());
        assertEquals(order.getStatus(), PENDING_PAYMENT);
    }

    @Test
    public void testAcceptPayment(){
        Order order = createOrder();

        acceptPayment(order);

        assertNotNull(order.getClientId());
        assertNotNull(order.getProductId());
        assertNotNull(order.getOrderId());
        assertNotNull(order.getPaymentId());
        assertEquals(order.getStatus(), PENDING_PRODUCT_ISSUE);
    }

    @Test
    public void testIssueProduct(){
        Order order = createOrder();
        acceptPayment(order);
        String rawInvoiceId = UUID.randomUUID().toString();
        InvoiceId invoiceId = InvoiceId.identifyInvoiceFrom(rawInvoiceId);

        order.issueProduct(invoiceId);

        assertNotNull(order.getClientId());
        assertNotNull(order.getProductId());
        assertNotNull(order.getOrderId());
        assertNotNull(order.getPaymentId());
        assertNotNull(order.getInvoiceId());
        assertEquals(order.getStatus(), PRODUCT_ISSUED);
    }

    private void acceptPayment(Order order){
        String rawPaymentId = UUID.randomUUID().toString();
        PaymentId paymentId = PaymentId.identifyPaymentFrom(rawPaymentId);
        order.acceptPayment(paymentId);
    }

    private Order createOrder(){
        String productId = UUID.randomUUID().toString();
        String clientId = UUID.randomUUID().toString();

        return Order.create(productId, clientId);
    }

}
