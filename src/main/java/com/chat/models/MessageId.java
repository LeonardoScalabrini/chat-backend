package com.chat.models;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Embeddable;

@Embeddable
public class MessageId implements Serializable {

  private final String id;

  public MessageId() {
    this.id = UUID.randomUUID().toString();
  }

  public String getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    MessageId messageId = (MessageId) o;
    return id.equals(messageId.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
