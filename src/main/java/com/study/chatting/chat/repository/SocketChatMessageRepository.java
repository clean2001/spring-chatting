package com.study.chatting.chat.repository;

import com.study.chatting.chat.domain.SocketChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SocketChatMessageRepository extends JpaRepository<SocketChatMessage, Long> {
    List<SocketChatMessage> findBySenderAndReceiver(String sender, String receiver);
}
