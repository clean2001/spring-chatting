package com.study.chatting.chat.service;

import com.study.chatting.chat.domain.ChatMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// SSE를 통해서 클라이언트에게 알림을 보내는 서비스
@Service
public class ChatService {
    private static final Map<String , SseEmitter> clients = new ConcurrentHashMap<>();

    // SseEmitter 객체 생성
    /* Long.MAX_VALUE: SSE 연결이 최대한 오래 유지되도록 설정하는데 사용된다.
    기본적으로 SseEmitter는 서버에서 클라이언트로 여러개의 이벤트를 비동기적으로 보낼수 있게 해주는 Spring MVC의 기능이다.

    Long.MAX_VALUE를 사용하면 기본 타임아웃보다 훨씬 더 긴 시간동안 연결이 유지된다.
    기본 타임아웃은 30초이지만, 여기서는 거의 무한대에 가까운 값으로 설정하여 연결이 끊기지 않도록 한다.

    clients.put(userId, emitter);
     */
    public SseEmitter subscribe(String userId) {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        clients.put(userId, emitter);
        emitter.onCompletion(() -> clients.remove(userId));
        emitter.onTimeout(() -> clients.remove(userId));
        return emitter;
    }

    public void sendNotification(String userId, ChatMessage message) {
        SseEmitter emitter = clients.get(userId);

        if(emitter != null) {
            try {
                emitter.send(message);
            } catch(IOException e) {
                clients.remove(userId);
            }
        }

    }

}
