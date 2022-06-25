package com.demo.chat.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Chat {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "sender_id")
    private UUID senderId;

    @Column(name = "recipient_id")
    private UUID recipientId;
}
