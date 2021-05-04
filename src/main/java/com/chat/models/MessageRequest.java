package com.chat.models;

public class MessageRequest {

    private String chat;

    private String message;

    private MessageRequest() {
    }

    public MessageRequest(String chat, String message) {
        super();
        this.chat = chat;
        this.message = message;
    }

    public String getChat() {
        return chat;
    }

    public String getMessage() {
        return message;
    }
}
