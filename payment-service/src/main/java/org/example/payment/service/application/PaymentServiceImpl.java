package org.example.payment.service.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.payment.service.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    private final PaymentAssembler paymentAssembler;
    private final PaymentRepository paymentRepository;
    private final DomainEventPublisher<PaymentCompleted> paymentCompletedEventPublisher;

    @Override
    @Transactional
    public PaymentDTO makePayment(PaymentDTO rawPayment) {
        log.info("Make payment");
        Payment payment = Payment.make(rawPayment.getAmount(), rawPayment.getOrderId());
        PaymentId paymentId = payment.getPaymentId();

        paymentRepository.save(payment);
        log.info("Payment with id {} has been saved", paymentId.getId());
        paymentCompletedEventPublisher.publish(payment.getDomainEventsByType(PaymentCompleted.class));
        log.info("PaymentCompleted event has been published");

        return paymentAssembler.writeDTO(payment);
    }

    @Override
    public PaymentDTO searchPayment(String id) {
        log.info("Search payment with id {}", id);
        PaymentId paymentId = PaymentId.identifyPaymentFrom(id);
        Payment payment = paymentRepository.paymentOfId(paymentId);
        log.info("Payment with id {} has been found", id);

        return paymentAssembler.writeDTO(payment);
    }

}
