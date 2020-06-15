package org.example.payment.service.presentation;

import lombok.RequiredArgsConstructor;
import org.example.payment.service.application.PaymentDTO;
import org.example.payment.service.application.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentDTO create(@RequestBody @Valid PaymentDTO paymentDTO){
        return paymentService.makePayment(paymentDTO);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PaymentDTO get(@PathVariable String id){
        return paymentService.searchPayment(id);
    }

}
