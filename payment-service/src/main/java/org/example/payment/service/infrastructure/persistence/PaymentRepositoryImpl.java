package org.example.payment.service.infrastructure.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.payment.service.domain.Payment;
import org.example.payment.service.domain.PaymentId;
import org.example.payment.service.domain.PaymentRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {

    private final PaymentJpaRepository paymentJpaRepository;

    @Override
    public Payment paymentOfId(PaymentId paymentId) {
        try{
            return paymentJpaRepository.findByPaymentId(paymentId)
                    .orElseThrow(() -> new PaymentNotFoundException(String.format("Payment with id %s is not found", paymentId.getId())));
        } catch (DataAccessException e){
            log.error(e.getMessage(), e);
            throw new RepositoryAccessException(e.getMessage(), e);
        }
    }

    @Override
    public void save(Payment payment) {
        try{
            paymentJpaRepository.save(payment);
        } catch (DataAccessException e){
            log.error(e.getMessage(), e);
            throw new RepositoryAccessException(e.getMessage(), e);
        }
    }

}
