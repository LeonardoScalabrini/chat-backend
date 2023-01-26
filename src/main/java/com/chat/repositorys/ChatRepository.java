package com.chat.repositorys;

import com.chat.models.Message;
import com.chat.models.MessageId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Message, MessageId> {

  List<Message> findAllByChat(String chat);
}
