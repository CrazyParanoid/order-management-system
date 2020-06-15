package org.example.order.service.domain;

import org.example.order.service.domain.supertype.DomainEvent;

import java.util.List;

public interface DomainEventPublisher<T extends DomainEvent> {

    void publish(List<T> domainEvents);

}
