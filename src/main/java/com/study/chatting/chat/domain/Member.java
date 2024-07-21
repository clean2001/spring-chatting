package com.study.chatting.chat.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<ChatMessage> sends;
    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    private List<ChatMessage> receives;


}

