package com.chat.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class Message {

    @EmbeddedId
    private MessageId messageId;

    private String message;

    private String chat;

    private Message() {

    }

    public Message(MessageId messageId, String message, String chat) {
        this();
        this.messageId = messageId;
        this.message = message;
        this.chat = chat;
    }

    public String getId() {
        return messageId.getId();
    }

    public String getMessage() {
        return message;
    }

    public String getChat() {
        return chat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return messageId.equals(message1.messageId) &&
                message.equals(message1.message) &&
                chat.equals(message1.chat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId, message, chat);
    }
}
