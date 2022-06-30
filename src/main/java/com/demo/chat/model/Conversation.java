package com.demo.chat.model;

import com.demo.chat.model.enums.ConversationType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "conversation")
@Getter
@Setter
public class Conversation {

    @Id
    @GeneratedValue
    private Long conversationId;

    @ManyToMany
    private Set<User> participants;

    @Column(name = "conversation_type")
    @Enumerated(EnumType.STRING)
    private ConversationType conversationType;

    @Column(name = "name")
    private String name;

}
