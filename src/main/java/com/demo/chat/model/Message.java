package com.demo.chat.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "messages")
@Getter
@Setter
public class Message {
    @Id
    @GeneratedValue
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private User recipient;
}
