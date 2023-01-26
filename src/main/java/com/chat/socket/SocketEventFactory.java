package com.chat.socket;

import com.chat.commands.SaveMessageCommand;
import com.chat.config.SocketConfig;
import com.chat.models.MessageRequest;
import com.chat.repositorys.ChatRepository;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SocketEventFactory {

  private static final String NAME_FORMAT = "/%s";
  private final SocketFactory socketFactory;
  private final SocketConfig socketConfig;
  private final ChatRepository repository;

  @Autowired
  public SocketEventFactory(
      SocketFactory socketFactory, SocketConfig socketConfig, ChatRepository repository) {
    this.socketFactory = socketFactory;
    this.socketConfig = socketConfig;
    this.repository = repository;
  }

  public SocketIOServer create() {
    SocketIOServer socketIOServer = socketFactory.create();
    socketConfig
        .getNamespaces()
        .forEach(
            namespace -> {
              final String name = String.format(NAME_FORMAT, namespace);
              final SocketIONamespace socketIONamespace = socketIOServer.addNamespace(name);
              socketIONamespace.addEventListener(
                  socketConfig.getMessageEvent(),
                  MessageRequest.class,
                  new SaveMessageCommand(repository, socketIONamespace, socketConfig));
            });
    return socketIOServer;
  }
}
