package com.study.chatting.chat.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.type.classreading.MethodsMetadataReader;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Member sender;
    @ManyToOne
    private Member receiver;
    private String content;
    private LocalDateTime timestamp;

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public ChatMessage(Member sender, Member receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
    }
}
