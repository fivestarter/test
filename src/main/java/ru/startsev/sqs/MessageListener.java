package ru.startsev.sqs;

import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @SqsListener("library.fifo")
    public void listen() {

    }
}
