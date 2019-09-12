package com.mageets.tcpinsteadofhttp;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.InitBinder;
import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;
import reactor.netty.tcp.TcpServer;

@Component
public class NettyTcpServer {

    private int port = 9000;

    private DisposableServer server;
    private MessageConsumer messageConsumer;

    public NettyTcpServer(MessageConsumer messageConsumer) {
        this.messageConsumer = messageConsumer;
        init();
    }

//    public void setPort(int port) {
//        this.port = port;
//    }

    public void init() {
        server = TcpServer
                .create()
                .host("127.0.0.1")
                .port(Integer.valueOf(port)).handle(((nettyInbound, nettyOutbound) -> {
                    nettyInbound.receive().asString()
                            .subscribe(messageConsumer::consume);
                    return Mono.never();
                }))
                .bindNow();

        System.out.printf("Starting server on %d%n", port);
//        server.onDispose().block();
    }

    public void stop() {
        server.dispose();
    }


}
