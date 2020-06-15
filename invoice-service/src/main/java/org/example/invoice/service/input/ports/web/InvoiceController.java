package org.example.invoice.service.input.ports.web;

import lombok.RequiredArgsConstructor;
import org.example.invoice.service.application.CommandProcessor;
import org.example.invoice.service.application.InvoiceIssueCommand;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final CommandProcessor<InvoiceIssueCommand> invoiceIssueCommandProcessor;

    @PostMapping(value = "/issue")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid InvoiceIssueCommand command){
        invoiceIssueCommandProcessor.process(command);
    }

}
