package com.study.chatting.chat.handler;

import com.study.chatting.chat.domain.Member;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// 웹소켓 연결을  관리하고 메시지를 브로드캐스트하는 핸들러이다.
public class ChatWebSocketHandler extends TextWebSocketHandler {
    // 웹 소켓 세션을 ConcurrentHashMap으로 관리한다.
    private static ConcurrentHashMap<Long, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 흠 이렇게 Member로 저장할 수도 있는건가?
        // member id 해야될 것 같기도 하고
        Member member = (Member) session.getAttributes().get("member");
        // key: 멤버 아이디, value: session
        sessions.put(member.getId(), session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Handle incoming message and send to receiver
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        Member member = (Member) session.getAttributes().get("member");
        sessions.remove(member.getId());
    }
}
