package com.chat.repositorys;

import com.chat.models.Message;
import com.chat.models.MessageId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Message, MessageId> {

    List<Message> findAllByChat(String chat);
}
