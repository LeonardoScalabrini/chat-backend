package com.chat.services;

import com.chat.models.Message;
import com.chat.repositorys.ChatRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

  private final ChatRepository chatRepository;

  @Autowired
  public ChatService(ChatRepository chatRepository) {
    this.chatRepository = chatRepository;
  }

  public List<Message> fetchMessages(String chat) {
    return chatRepository.findAllByChat(chat);
  }
}
