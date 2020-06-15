package org.example.order.service.application;

public interface CommandProcessor<T extends Command> {

    void process(T command);

}
