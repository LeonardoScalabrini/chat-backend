package com.chat.socket;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import com.chat.config.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SocketApplicationTest {

  @Mock private SocketEventFactory socketEventFactory;

  @Mock private SocketConfig socketConfig;

  @Mock private SocketIOServer socketIOServer;

  @InjectMocks private SocketApplication socketApplication;

  @Test
  public void startSocket() throws NoSuchMethodException, InterruptedException {
    assertNotNull(SocketApplication.class.getMethod("startSocket").getDeclaredAnnotations()[0]);
    when(socketEventFactory.create()).thenReturn(socketIOServer);
    when(socketConfig.getSocketSleep()).thenReturn(0);
    socketApplication.startSocket();
    verify(socketEventFactory, times(1)).create();
    verify(socketConfig, times(1)).getSocketSleep();
    verify(socketIOServer, times(1)).start();
    verify(socketIOServer, times(1)).stop();
  }
}
