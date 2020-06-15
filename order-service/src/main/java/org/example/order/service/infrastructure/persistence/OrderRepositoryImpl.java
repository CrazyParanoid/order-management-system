package org.example.order.service.infrastructure.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.order.service.domain.Order;
import org.example.order.service.domain.OrderId;
import org.example.order.service.domain.OrderRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;


    @Override
    public Order orderOfId(OrderId orderId) {
        try{
            return orderJpaRepository.findByOrderId(orderId)
                    .orElseThrow(() -> new OrderNotFoundException(String.format("Order with id %s is not found", orderId.getId())));
        } catch (DataAccessException e){
            log.error(e.getMessage(), e);
            throw new RepositoryAccessException(e.getMessage(), e);
        }
    }

    @Override
    public void save(Order order) {
        try{
            orderJpaRepository.save(order);
        } catch (DataAccessException e){
            log.error(e.getMessage(), e);
            throw new RepositoryAccessException(e.getMessage(), e);
        }
    }

}
