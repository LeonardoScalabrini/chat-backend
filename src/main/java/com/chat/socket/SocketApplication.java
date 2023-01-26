package com.chat.socket;

import com.chat.config.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SocketApplication {

  private final SocketEventFactory socketEventFactory;
  private final SocketConfig socketConfig;

  @Autowired
  public SocketApplication(SocketEventFactory socketEventFactory, SocketConfig socketConfig) {
    this.socketEventFactory = socketEventFactory;
    this.socketConfig = socketConfig;
  }

  @EventListener(ApplicationReadyEvent.class)
  public void startSocket() throws InterruptedException {
    final SocketIOServer server = socketEventFactory.create();
    server.start();
    Thread.sleep(socketConfig.getSocketSleep());
    server.stop();
  }
}
