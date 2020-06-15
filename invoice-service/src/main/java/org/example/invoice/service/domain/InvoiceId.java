package org.example.invoice.service.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.invoice.service.domain.supertype.ValueObject;

import javax.persistence.Embeddable;
import java.util.Objects;
import java.util.UUID;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class InvoiceId implements ValueObject {

    private String id;

    public static InvoiceId identifyInvoice(){
        UUID uuid = UUID.randomUUID();
        return new InvoiceId(uuid.toString());
    }

    public static InvoiceId identifyInvoiceFrom(String id){
        return new InvoiceId(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        InvoiceId invoiceId = (InvoiceId) o;
        return Objects.equals(id, invoiceId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
