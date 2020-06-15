package org.example.order.service.infrastructure.persistence;

import org.example.order.service.domain.Order;
import org.example.order.service.domain.OrderId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByOrderId(OrderId orderId);

}
