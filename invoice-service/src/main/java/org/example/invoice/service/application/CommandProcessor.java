package org.example.invoice.service.application;

public interface CommandProcessor<T extends Command> {

    void process(T command);

}
