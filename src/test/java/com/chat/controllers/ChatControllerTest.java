package com.chat.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.chat.models.Message;
import com.chat.models.MessageId;
import com.chat.services.ChatService;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ChatControllerTest {

  @MockBean private ChatService chatService;

  @Autowired private MockMvc mockMvc;

  @Before
  public void init() {
    Message messageDB = new Message(new MessageId(), "message", "chat");
    when(chatService.fetchMessages("chat")).thenReturn(Collections.singletonList(messageDB));
  }

  @Test
  public void shouldFetchMessages() throws Exception {
    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/chat/messages/chat")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.[0].message").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.[0].chat").exists());

    verify(chatService, times(1)).fetchMessages("chat");
  }

  @Test
  public void shouldEmptyFetchMessages() throws Exception {
    when(chatService.fetchMessages("chat")).thenReturn(Collections.emptyList());
    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/chat/messages/chat")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());

    verify(chatService, times(1)).fetchMessages("chat");
  }
}
