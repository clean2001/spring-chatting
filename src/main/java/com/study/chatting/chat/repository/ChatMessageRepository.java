package com.study.chatting.chat.repository;

import com.study.chatting.chat.domain.ChatMessage;
import com.study.chatting.chat.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByReceiver(Member receiver);
}
