package org.example.order.service.domain.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.order.service.domain.supertype.ValueObject;

import javax.persistence.Embeddable;
import java.util.Objects;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceId implements ValueObject {

    private String id;

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
