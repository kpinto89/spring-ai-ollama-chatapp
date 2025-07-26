package org.demo.chatapp.controller;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final OllamaChatModel chatModel;

    @Autowired
    public ChatController(OllamaChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @PostMapping
    public String chat(@RequestBody String prompt) {
        Prompt userPrompt = new Prompt(List.of(new UserMessage(prompt)));
        ChatResponse response = chatModel.call(userPrompt);
        return response.getResult().getOutput().getText(); // Output is a String
    }
}