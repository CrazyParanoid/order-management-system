package org.example.payment.service.domain;

import org.example.payment.service.domain.supertype.DomainEvent;

import java.util.List;

public interface DomainEventPublisher<T extends DomainEvent> {

    void publish(List<T> domainEvents);

}
