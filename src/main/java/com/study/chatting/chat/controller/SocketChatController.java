package com.study.chatting.chat.controller;

import com.study.chatting.chat.domain.SocketChatMessage;
import com.study.chatting.chat.repository.SocketChatMessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
public class SocketChatController {

    @Autowired
    private SocketChatMessageRepository socketChatMessageRepository;

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/chat.sendMessage") // 웹소켓 메시지를 특정 경로로 매핑한다.
    @SendToUser("/queue/reply")
    public void sendMessage(SocketChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        log.info("line 33: {}", chatMessage);
        socketChatMessageRepository.save(chatMessage);
        messagingTemplate.convertAndSendToUser(chatMessage.getReceiver(), "/queue/reply", chatMessage);
    }

    @GetMapping("/api/messages/{sender}/{receiver}")
    @ResponseBody
    public List<SocketChatMessage> getMessages(@PathVariable String sender, @PathVariable String receiver) {
        return socketChatMessageRepository.findBySenderAndReceiver(sender, receiver);
    }

    @PostMapping("/api/messages")
    @ResponseBody
    public SocketChatMessage saveMessage(@RequestBody SocketChatMessage chatMessage) {
        return socketChatMessageRepository.save(chatMessage);
    }
}

