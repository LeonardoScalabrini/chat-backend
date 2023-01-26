package com.chat.socket;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.*;

import com.chat.commands.SaveMessageCommand;
import com.chat.config.SocketConfig;
import com.chat.models.MessageRequest;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SocketEventFactoryTest {

  @Mock private SocketConfig socketConfig;

  @Mock private SocketFactory socketFactory;

  @Mock private SocketIOServer socketIOServer;

  @Mock private SocketIONamespace socketIONamespace;

  @InjectMocks private SocketEventFactory socketEventFactory;

  @Test
  public void create() {
    when(socketFactory.create()).thenReturn(socketIOServer);
    when(socketConfig.getNamespaces()).thenReturn(asList("namespace1", "namespace2"));
    when(socketConfig.getMessageEvent()).thenReturn("event");
    when(socketIOServer.addNamespace("/namespace1")).thenReturn(socketIONamespace);
    when(socketIOServer.addNamespace("/namespace2")).thenReturn(socketIONamespace);
    SocketIOServer socketIOServer = socketEventFactory.create();
    verify(socketFactory, times(1)).create();
    verify(socketConfig, times(1)).getNamespaces();
    verify(socketIOServer, times(1)).addNamespace("/namespace1");
    verify(socketIOServer, times(1)).addNamespace("/namespace2");
    verify(socketIONamespace, times(2))
        .addEventListener(
            eq("event"), eq(MessageRequest.class), ArgumentMatchers.any(SaveMessageCommand.class));
    verify(socketConfig, times(2)).getMessageEvent();
  }
}
