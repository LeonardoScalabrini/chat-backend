package com.chat.services;

import com.chat.models.Message;
import com.chat.models.MessageId;
import com.chat.repositorys.ChatRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ChatServiceTest {

    @InjectMocks
    private ChatService chatService;

    @Mock
    private ChatRepository chatRepository;

    private Message messageDB;

    @Before
    public void init() {
        messageDB = new Message(new MessageId(), "message", "chat");
        when(chatRepository.findAllByChat("chat")).thenReturn(singletonList(messageDB));
    }

    @Test
    public void fetchMessages() {
        assertEquals(singletonList(messageDB), chatService.fetchMessages("chat"));
        verify(chatRepository, times(1)).findAllByChat("chat");
    }

    @Test
    public void fetchEmptyMessages() {
        when(chatRepository.findAllByChat("chat")).thenReturn(emptyList());
        assertEquals(emptyList(), chatService.fetchMessages("chat"));
        verify(chatRepository, times(1)).findAllByChat("chat");
    }
}