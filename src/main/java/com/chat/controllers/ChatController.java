package com.chat.controllers;

import com.chat.models.Message;
import com.chat.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/messages/{filtro}")
    public List<Message> fetchMessages(@PathVariable("filtro") String filtro) {
        return chatService.fetchMessages(filtro);
    }
}
