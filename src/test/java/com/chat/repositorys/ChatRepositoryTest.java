package com.chat.repositorys;

import com.chat.models.Message;
import com.chat.models.MessageId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ChatRepositoryTest {

    @Autowired
    private ChatRepository chatRepository;

    private Message messageDB;
    private Message messageOuther;

    @Before
    public void init() {
        messageDB = new Message(new MessageId(), "message", "chat");
        messageOuther = new Message(new MessageId(), "messageOuther", "outher");
        chatRepository.save(messageDB);
        chatRepository.save(messageOuther);
    }

    @After
    public void after() {
        chatRepository.deleteAll();
    }

    @Test
    public void shouldFindByChat() {
        List<Message> chatMessages = chatRepository.findAllByChat("chat");
        assertEquals(1, chatMessages.size());
        assertEquals(messageDB, chatMessages.get(0));
        List<Message> outherChatMessages = chatRepository.findAllByChat("outher");
        assertEquals(1, outherChatMessages.size());
        assertEquals(messageOuther, outherChatMessages.get(0));
        List<Message> chatEmptyMessages = chatRepository.findAllByChat("chatEmpty");
        assertTrue(chatEmptyMessages.isEmpty());
    }
}