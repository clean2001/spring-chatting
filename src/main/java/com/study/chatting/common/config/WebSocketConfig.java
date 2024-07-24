package com.study.chatting.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;


@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // queue 로 클라이언트로 메시지 전달(sub)
        config.enableSimpleBroker("/sub");
        // @Controller 객체의 @MessageMapping 메서드로 라우팅, 클라이언트가 서버로 메시지 보낼 URL 접두사(pub)
        config.setApplicationDestinationPrefixes("/pub");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry
                .addEndpoint("/ws") //web socket connection이 최초로 이루어지는 곳(handshake)
                .setAllowedOriginPatterns("*");
//                .withSockJS();
    }
}
