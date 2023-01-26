package com.chat.config;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SocketConfig {

  private final String hostname;

  private final int port;

  private final String messageEvent;

  private final List<String> namespaces;

  private final Integer socketSleep;

  @Autowired
  public SocketConfig(
      @Value("${socket.hostname}") String hostname,
      @Value("${socket.port}") int port,
      @Value("${socket.message.event}") String messageEvent,
      @Value("${socket.sleep}") Integer socketSleep,
      @Value("#{'${socket.chat.namespaces}'.split(',')}") List<String> namespaces) {
    this.hostname = hostname;
    this.port = port;
    this.messageEvent = messageEvent;
    this.namespaces = namespaces;
    this.socketSleep = socketSleep;
  }

  public String getHostname() {
    return hostname;
  }

  public int getPort() {
    return port;
  }

  public String getMessageEvent() {
    return messageEvent;
  }

  public List<String> getNamespaces() {
    return namespaces;
  }

  public Integer getSocketSleep() {
    return socketSleep;
  }
}
