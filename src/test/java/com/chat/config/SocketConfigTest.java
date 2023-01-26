package com.chat.config;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SocketConfigTest {

  @Autowired private SocketConfig socketConfig;

  @Test
  public void getHostname() {
    assertEquals("localhost", socketConfig.getHostname());
  }

  @Test
  public void getPort() {
    assertEquals(9092, socketConfig.getPort());
  }

  @Test
  public void getMessageEvent() {
    assertEquals("message", socketConfig.getMessageEvent());
  }

  @Test
  public void getSocketSleep() {
    assertEquals(new Integer(0), socketConfig.getSocketSleep());
  }

  @Test
  public void getNamespaces() {
    assertEquals(2, socketConfig.getNamespaces().size());
    assertEquals("chat1", socketConfig.getNamespaces().get(0));
    assertEquals("chat2", socketConfig.getNamespaces().get(1));
  }
}
