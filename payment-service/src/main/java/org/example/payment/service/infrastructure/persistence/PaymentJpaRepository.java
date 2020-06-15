package org.example.payment.service.infrastructure.persistence;

import org.example.payment.service.domain.Payment;
import org.example.payment.service.domain.PaymentId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentJpaRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByPaymentId(PaymentId paymentId);

}
