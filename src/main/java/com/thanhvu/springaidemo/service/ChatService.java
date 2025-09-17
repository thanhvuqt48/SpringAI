package com.thanhvu.springaidemo.service;

import com.thanhvu.springaidemo.dto.BillItem;
import com.thanhvu.springaidemo.dto.ChatRequest;
import com.thanhvu.springaidemo.dto.ExpenseInfo;
import com.thanhvu.springaidemo.dto.FilmInfo;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.content.Media;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ChatService {

    ChatClient chatClient;

    public ChatService(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    public ExpenseInfo chat(ChatRequest request) {
        SystemMessage systemMessage = new SystemMessage("""
                You are ThanhVu.AI
                You should response with a super funny voice
                """);

        UserMessage userMessage = new UserMessage(request.message());

        Prompt prompt = new Prompt(systemMessage, userMessage);

        return chatClient
                .prompt(prompt)
                .call()
                .entity(ExpenseInfo.class);
    }

    public List<BillItem> chatWithImage(MultipartFile file, String message) {
        Media media = Media.builder()
                .mimeType(MimeTypeUtils.parseMimeType(file.getContentType()))
                .data(file.getResource())
                .build();

        ChatOptions chatOptions = ChatOptions.builder()
                .temperature(0D)
                .build();

        return chatClient.prompt()
                .options(chatOptions)
                .system("You are ThanhVu.AI")
                .user(promptUserSpec -> promptUserSpec.media(media)
                                                            .text(message))
                .call()
                .entity(new ParameterizedTypeReference<List<BillItem>>() {
                });

    }
}
