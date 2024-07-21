package com.study.chatting.chat.controller;

import com.study.chatting.chat.domain.ChatMessage;
import com.study.chatting.chat.domain.Member;
import com.study.chatting.chat.dto.ChatReqDto;
import com.study.chatting.chat.dto.ChatResDto;
import com.study.chatting.chat.repository.ChatMessageRepository;
import com.study.chatting.chat.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatRestController {
    private final ChatMessageRepository chatMessageRepository;
    private final MemberRepository memberRepository;

    @GetMapping("/messages/{memberId}")
    public List<ChatMessage> getMessages(@PathVariable Long memberId) {
        return chatMessageRepository.findByReceiverId(memberId);
    }

    // dto에다가 보내는 사람, 받는 사람 담아서 보내고, DB에 저장하고.
    //
    @PostMapping("/send")
    public ChatResDto sendMessage(@RequestBody ChatReqDto chatMessage) {

        Member send = memberRepository.findById(chatMessage.getSender()).orElseThrow(() -> new IllegalArgumentException("으악"));
        Member receive = memberRepository.findById(chatMessage.getReceiver()).orElseThrow(() -> new IllegalArgumentException("으엥"));
        ChatMessage chat = chatMessage.toEntity(send, receive, chatMessage.getContent());
        chat.setTimestamp(LocalDateTime.now());

        chatMessageRepository.save(chat);

        return ChatResDto.fromEntity(chat);
    }


    // 이건또 뭐야 왜 회원가입이 있음
    @PostMapping("/members")
    public Member createMember(@RequestBody Member member) {
        return memberRepository.save(member);
    }
}
