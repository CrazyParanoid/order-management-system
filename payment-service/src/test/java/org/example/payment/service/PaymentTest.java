package org.example.payment.service;

import lombok.extern.slf4j.Slf4j;
import org.example.payment.service.application.PaymentDTO;
import org.example.payment.service.domain.Payment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.assertNotNull;

@Slf4j
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {Application.class})
public class PaymentTest {

    @Test
    public void testCreatePayment(){
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setAmount(650000.00);
        paymentDTO.setOrderId(UUID.randomUUID().toString());

        Payment payment = Payment.make(paymentDTO.getAmount(), paymentDTO.getOrderId());

        assertNotNull(payment.getAmount());
        assertNotNull(payment.getPaymentId());
        assertNotNull(payment.getOrderId());
    }

}
