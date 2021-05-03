package com.chat.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class Message {

    @EmbeddedId
    private MessageId id;

    private String value;

    private String chat;

    private Message() {

    }

    public Message(MessageId id, String value, String chat) {
        this();
        this.id = id;
        this.value = value;
        this.chat = chat;
    }

    public MessageId getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public String getChat() {
        return chat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id.equals(message.id) &&
                value.equals(message.value) &&
                chat.equals(message.chat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, chat);
    }
}
