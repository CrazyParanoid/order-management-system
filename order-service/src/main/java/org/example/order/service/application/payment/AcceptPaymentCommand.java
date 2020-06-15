package org.example.order.service.application.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.order.service.application.Command;

@Data
@AllArgsConstructor
public class AcceptPaymentCommand implements Command {

    private String orderId;
    private String paymentId;

}
