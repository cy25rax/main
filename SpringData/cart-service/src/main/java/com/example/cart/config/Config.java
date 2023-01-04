package com.example.cart.config;

import com.example.cart.integration.ProductServiceIntegrationProperties;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;


@Configuration
@EnableConfigurationProperties(
        ProductServiceIntegrationProperties.class
)
public class Config {
    @Autowired
    private ProductServiceIntegrationProperties properties;

    @Bean
    public WebClient productServiceWebIntegration() {
        TcpClient tcpClient = TcpClient
                .create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, properties.getConnectTimeout())
                .doOnConnected(connection -> {
                   connection.addHandlerLast(new ReadTimeoutHandler(properties.getReadTimeout()));
                   connection.addHandlerLast(new WriteTimeoutHandler(properties.getWriteTimeout()));
                });
        return WebClient.builder()
                .baseUrl(properties.getUrl())
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                .build();
    }

}
