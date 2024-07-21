package com.study.chatting.chat.dto;

import com.study.chatting.chat.domain.ChatMessage;
import com.study.chatting.chat.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ChatReqDto {
    private Long sender;
    private Long receiver;
    private String content;

    public ChatMessage toEntity(Member sender, Member receiver, String content) {
        return new ChatMessage(sender, receiver, content);
    }
}
