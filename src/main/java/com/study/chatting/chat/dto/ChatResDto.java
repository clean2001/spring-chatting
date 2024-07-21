package com.study.chatting.chat.dto;

import com.study.chatting.chat.domain.ChatMessage;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ChatResDto {
    Long sender;
    Long receiver;
    String content;
    LocalDateTime timestamp;

    public static ChatResDto fromEntity(ChatMessage message) {
        return ChatResDto.builder()
                .sender(message.getSender().getId())
                .receiver(message.getReceiver().getId())
                .content(message.getContent())
                .timestamp(message.getTimestamp()).build();
    }
}
