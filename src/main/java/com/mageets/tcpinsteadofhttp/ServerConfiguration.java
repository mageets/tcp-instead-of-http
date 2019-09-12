package com.mageets.tcpinsteadofhttp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ServerConfiguration {

    @Bean
    @Profile("!test")
    public TcpServer tcpServer() {
        TcpServer tcpServer = new TcpServer();
        return tcpServer;
    }

}
