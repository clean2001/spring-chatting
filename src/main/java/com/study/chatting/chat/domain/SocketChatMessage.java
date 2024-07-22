package com.study.chatting.chat.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@ToString
@Getter
@Entity
public class SocketChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private String sender;
    private String receiver;

}
