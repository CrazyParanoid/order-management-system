package org.example.payment.service.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {

    private String id;
    @NotEmpty
    private String orderId;
    @NotNull
    private Double amount;

}
