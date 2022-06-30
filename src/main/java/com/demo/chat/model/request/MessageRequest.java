package com.demo.chat.model.request;

import com.demo.chat.model.Conversation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageRequest {
    String content;
    Conversation conversation;
}
