package com.study.chatting.chat.repository;

import com.study.chatting.chat.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);
}