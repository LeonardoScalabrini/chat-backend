package com.chat.services;

import com.chat.models.Message;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class ChatService {
    public Message sendMessage(String message) {
        Message m = new Message();
        m.setValue(message);
        m.setId(Integer.toString(new Random().nextInt()));
        return m;
    }

    public List<Message> fetchMessages() {
        Message m = new Message();
        m.setValue("Olá");
        m.setId(Integer.toString(new Random().nextInt()));
        return Collections.singletonList(m);
    }
}
