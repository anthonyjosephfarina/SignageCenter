package com.guidewire.signagecenter.config;

import com.guidewire.signagecenter.messaging.RabbitMQProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    @Autowired
    private RabbitMQProperties rabbitMQProperties;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");

        // RabbitMQ support
        registry.enableStompBrokerRelay("/topic")
                .setRelayHost(rabbitMQProperties.getHost())
                .setRelayPort(rabbitMQProperties.getPort())
                .setClientLogin(rabbitMQProperties.getUsername())
                .setClientPasscode(rabbitMQProperties.getPassword());
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
    }
}
