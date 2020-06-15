package org.example.order.service.application.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.order.service.application.Command;

@Data
@AllArgsConstructor
public class ProductIssuedCommand implements Command {

    private String orderId;
    private String invoiceId;

}
