package com.mageets.tcpinsteadofhttp;

import org.springframework.stereotype.Service;

@Service("consoleMessageConsumer")
public class ConsoleConsumer implements MessageConsumer {
    @Override
    public void consume(String s) {
        System.out.printf("Message...%s", s);
    }
}
