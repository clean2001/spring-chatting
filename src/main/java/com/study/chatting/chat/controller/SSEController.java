//package com.study.chatting.chat.controller;
//
//import com.study.chatting.chat.domain.ChatMessage;
//import com.study.chatting.chat.service.ChatService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
//
//
//// SSE 컨트롤러: 클라이언트가 SSE를 구독하고 메시지를 전송할 수 있게 하는 컨트롤러
//@RequiredArgsConstructor
//@RestController
//@RequestMapping("/sse")
//public class SSEController {
//    private final ChatService chatService;
//
//    @GetMapping("/subscribe/{userId}")
//    public SseEmitter subscribe(@PathVariable String userId) {
//        return chatService.subscribe(userId);
//    }
//
//    @PostMapping("/send")
//    public void send(@RequestBody ChatMessage message) {
//        chatService.sendNotification(message.getReceiver(), message);
//    }
//}
