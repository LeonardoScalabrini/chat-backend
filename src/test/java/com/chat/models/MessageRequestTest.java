package com.chat.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MessageRequestTest {

  private MessageRequest messageRequest = new MessageRequest("chat", "message");

  @Test
  public void getChat() {
    assertEquals("chat", messageRequest.getChat());
  }

  @Test
  public void getMessage() {
    assertEquals("message", messageRequest.getMessage());
  }
}
