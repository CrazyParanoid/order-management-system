package org.example.payment.service.domain.supertype;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class AggregateRoot extends IdentifiedDomainObject implements Entity{

    @Transient
    private List<DomainEvent> domainEvents = new ArrayList<>();

    public void registerDomainEvent(DomainEvent domainEvent){
        this.domainEvents.add(domainEvent);
    }

    public <T extends DomainEvent> List<T> getDomainEventsByType(Class<T> clazz){
        return this.domainEvents.stream()
                .filter(clazz::isInstance)
                .map(clazz::cast)
                .collect(Collectors.toList());
    }

}
