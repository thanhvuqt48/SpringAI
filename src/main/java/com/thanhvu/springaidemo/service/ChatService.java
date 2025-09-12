package com.thanhvu.springaidemo.service;

import com.thanhvu.springaidemo.dto.ChatRequest;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ChatService {

    ChatClient chatClient;

    public ChatService(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    public String chat(ChatRequest request) {
        return chatClient
                .prompt(request.message())
                .call()
                .content();
    }
}
