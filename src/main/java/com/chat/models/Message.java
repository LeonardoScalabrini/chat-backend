package com.chat.models;

import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Message {

  @EmbeddedId private MessageId messageId;

  private String value;

  private String chat;

  private Message() {}

  public Message(MessageId messageId, String value, String chat) {
    this();
    this.messageId = messageId;
    this.value = value;
    this.chat = chat;
  }

  public String getId() {
    return messageId.getId();
  }

  public String getMessage() {
    return value;
  }

  public String getChat() {
    return chat;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Message message1 = (Message) o;
    return messageId.equals(message1.messageId)
        && value.equals(message1.value)
        && chat.equals(message1.chat);
  }

  @Override
  public int hashCode() {
    return Objects.hash(messageId, value, chat);
  }
}
