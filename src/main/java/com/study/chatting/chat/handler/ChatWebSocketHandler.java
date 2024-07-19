package com.study.chatting.chat.handler;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// 웹소켓 연결을  관리하고 메시지를 브로드캐스트하는 핸들러이다.
public class ChatWebSocketHandler extends TextWebSocketHandler {
    // 웹 소켓 세션이 뭘까. 웹 소켓 세션을 ConcurrentHashMap으로 관리하는구나.
    private static final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.put(session.getId(), session); // 커넥션이 연결되면 WebSocketSession을 해시 맵에 넣어놓고,
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // TODO: 매개변수로 들어온 세션과 일치하는지 확인하는 로직이 필요하지 않을까? 왜 그런게 안보이지?

        for(WebSocketSession webSocketSession : sessions.values()) {
            if(webSocketSession.isOpen()) {
                webSocketSession.sendMessage(message);
            }
        }
    }
}
