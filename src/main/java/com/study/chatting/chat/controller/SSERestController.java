package com.study.chatting.chat.controller;

import com.study.chatting.chat.domain.ChatMessage;
import com.study.chatting.chat.domain.Member;
import com.study.chatting.chat.dto.ChatReqDto;
import com.study.chatting.chat.repository.ChatMessageRepository;
import com.study.chatting.chat.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@RestController
@RequestMapping("/sse")
public class SSERestController {
    private static ConcurrentHashMap<Long, SseEmitter> emitters = new ConcurrentHashMap<>();
    private final ChatMessageRepository chatMessageRepository;
    private final MemberRepository memberRepository;

    // 누가 로그인해서 특정 채팅방에 들어가면 이렇게 되는건가?
    // TODO: 로그인 하면 무조건 알림이 오도록 할건데.. 어떻게 해야하지
    // TODO: 이부분 강사님께 질문!!!
    @GetMapping("/subscribe/{memberId}")
    public SseEmitter subscribe(@PathVariable Long memberId) {
        // 시간 세팅
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);

        // 여기서 memberId는 리시버인거지
        // TODO: 그럼 receiver가 A라고 치자. 그럼 B한테도 채팅 오고, C한테도 채팅올건데 어떻게 구분함?
        // => 아 응답 아이디 있으니까 구분할 수 있구만
        emitters.put(memberId, emitter);
        emitter.onCompletion(() -> emitters.remove(memberId));
        emitter.onTimeout(() -> emitters.remove(memberId));
        return emitter;
    }

    // 메시지 보내기~
    // sse emitter 받아와서, 널이 아니면 알림 보내버리기
    @PostMapping("/send")
    public void sendMessage(@RequestBody ChatReqDto chatMessage) throws IOException {
        Member send = memberRepository.findById(chatMessage.getSender()).orElseThrow(() -> new IllegalArgumentException("으악"));
        Member receive = memberRepository.findById(chatMessage.getReceiver()).orElseThrow(() -> new IllegalArgumentException("으엥"));
        ChatMessage chat = chatMessage.toEntity(send, receive, chatMessage.getContent());
        chat.setTimestamp(LocalDateTime.now());

        chatMessageRepository.save(chat);

        SseEmitter emitter = emitters.get(chat.getReceiver().getId());
        if (emitter != null) {
            emitter.send(chatMessage);
        }
    }
}