//package com.study.chatting.chat.controller;
//
//
//import com.study.chatting.chat.domain.ChatMessage;
//import com.study.chatting.chat.service.ChatService;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.stereotype.Controller;
//
//@Controller
//public class ChatController {
//
//    private final ChatService chatService;
//
//    public ChatController(ChatService chatService) {
//        this.chatService = chatService;
//    }
//
//    @MessageMapping("/chat")
//    @SendTo("/topic/messages")
//    public ChatMessage sendMessage(ChatMessage message) {
//        chatService.sendNotification(message.getReceiver(), message);
//        return message;
//    }
//}
