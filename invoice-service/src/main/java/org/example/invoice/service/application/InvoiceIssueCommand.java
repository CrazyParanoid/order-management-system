package org.example.invoice.service.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceIssueCommand implements Command{

    @NotEmpty
    private String invoiceId;

}
