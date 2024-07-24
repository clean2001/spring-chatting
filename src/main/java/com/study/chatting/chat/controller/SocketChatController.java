package com.study.chatting.chat.controller;

import com.study.chatting.chat.domain.SocketChatMessage;
import com.study.chatting.chat.repository.SocketChatMessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
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

    // 그러면 이거의 정체는 뭐지?
    @MessageMapping("/chatroom/{roomNumber}") // 웹소켓 메시지를 특정 경로로 매핑한다. (pub/chatroom/1)
//    @SendTo("/sub/chatroom/{roomNumber}")
    public void sendMessage(SocketChatMessage chatMessage, @DestinationVariable String roomNumber) {
        log.info("line 33: {}", chatMessage);
        socketChatMessageRepository.save(chatMessage); // 메시지를 저장한다.
        messagingTemplate.convertAndSend("/sub/chatroom/" + roomNumber, chatMessage);
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

