package com.study.chatting.chat.domain;

import lombok.Getter;

@Getter
public class ChatMessage {
    private String sender;
    private String receiver;
    private String content;
}
