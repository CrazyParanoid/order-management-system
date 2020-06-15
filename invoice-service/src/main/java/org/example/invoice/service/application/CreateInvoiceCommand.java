package org.example.invoice.service.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateInvoiceCommand implements Command {

    private String orderId;
    private String paymentId;

}
