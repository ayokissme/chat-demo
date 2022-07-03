package com.demo.chat.model;

import com.demo.chat.model.enums.ConversationType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
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
    private Set<User> participants = new HashSet<>();

    @Column(name = "conversation_type")
    @Enumerated(EnumType.STRING)
    private ConversationType conversationType;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image = "no-conv-picture.png";

    @Column(name = "last_message")
    private String lastMessage;

    @Column(name = "last_message_sender_id")
    private UUID lastMessageSenderId;

    @Column(name = "secret_key")
    private UUID secretKey;

    @Column(name = "timestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime lastMessageCreatedAt;

    public void addParticipant(User user) {
        this.participants.add(user);
    }
}
