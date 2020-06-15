package org.example.invoice.service.infrastructure.persistence;

public class InvoiceNotFoundException extends RuntimeException{

    public InvoiceNotFoundException() {
    }

    public InvoiceNotFoundException(String message) {
        super(message);
    }

    public InvoiceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvoiceNotFoundException(Throwable cause) {
        super(cause);
    }

    public InvoiceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
