package com.chat.socket;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.chat.config.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SocketFactoryTest {

  @Mock private SocketConfig socketConfig;

  @InjectMocks private SocketFactory socketFactory;

  @Test
  public void create() {
    when(socketConfig.getHostname()).thenReturn("hostname");
    when(socketConfig.getPort()).thenReturn(10);
    SocketIOServer socketIOServer = socketFactory.create();
    verify(socketConfig, times(1)).getHostname();
    verify(socketConfig, times(1)).getPort();
    assertEquals("hostname", socketIOServer.getConfiguration().getHostname());
    assertEquals(10, socketIOServer.getConfiguration().getPort());
  }
}
