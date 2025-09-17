package com.thanhvu.springaidemo.controller;

import com.thanhvu.springaidemo.dto.BillItem;
import com.thanhvu.springaidemo.dto.ChatRequest;
import com.thanhvu.springaidemo.dto.ExpenseInfo;
import com.thanhvu.springaidemo.dto.FilmInfo;
import com.thanhvu.springaidemo.service.ChatService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ChatController {

    ChatService chatService;

    @PostMapping("/chat")
    public ExpenseInfo chat(@RequestBody ChatRequest request) {
        return chatService.chat(request);
    }

    @PostMapping("/chat-with-image")
    List<BillItem> chatWithImage(@RequestParam("file") MultipartFile file, @RequestParam("message") String message) {
        return chatService.chatWithImage(file, message);
    }
}
