package com.thanhvu.springaidemo.controller;

import com.thanhvu.springaidemo.dto.ChatRequest;
import com.thanhvu.springaidemo.service.ChatService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ChatController {

    ChatService chatService;

    @PostMapping("/chat")
    public String chat(@RequestBody ChatRequest request) {
        return chatService.chat(request);
    }
}
