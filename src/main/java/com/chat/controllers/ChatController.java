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
    @RequestMapping(value = "/message", method = RequestMethod.POST)
    public Message sendMessage(@RequestBody  String message) {
        return chatService.sendMessage(message);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public List<Message> fetchMessages() {
        return chatService.fetchMessages();
    }
}
