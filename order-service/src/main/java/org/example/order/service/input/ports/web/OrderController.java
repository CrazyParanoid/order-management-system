package org.example.order.service.input.ports.web;

import lombok.RequiredArgsConstructor;
import org.example.order.service.application.order.OrderDTO;
import org.example.order.service.application.order.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO create(@RequestBody @Valid OrderDTO orderDTO){
        return orderService.createOrder(orderDTO);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDTO get(@PathVariable String id){
        return orderService.searchOrder(id);
    }

}
