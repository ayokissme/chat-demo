package com.demo.chat.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class MessageRequest {
    String content;
    UUID recipient;
}
