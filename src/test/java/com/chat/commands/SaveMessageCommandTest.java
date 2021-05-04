package com.chat.commands;

import com.chat.config.SocketConfig;
import com.chat.models.Message;
import com.chat.models.MessageRequest;
import com.chat.repositorys.ChatRepository;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.BroadcastOperations;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIONamespace;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SaveMessageCommandTest {

    @Mock
    private ChatRepository chatRepository;

    @Mock
    private SocketIONamespace socketIONamespace;

    @Mock
    private BroadcastOperations broadcastOperations;

    @Mock
    private SocketConfig socketConfig;

    @Mock
    private SocketIOClient socketIOClient;

    @Mock
    private MessageRequest messageRequest;

    @Mock
    private AckRequest ackRequest;

    @Test
    public void onData() {
        when(messageRequest.getMessage()).thenReturn("message");
        when(messageRequest.getChat()).thenReturn("chat");
        when(socketIONamespace.getBroadcastOperations()).thenReturn(broadcastOperations);
        when(socketConfig.getMessageEvent()).thenReturn("message-event");
        SaveMessageCommand saveMessageCommand = new SaveMessageCommand(chatRepository, socketIONamespace, socketConfig);
        saveMessageCommand.onData(socketIOClient, messageRequest, ackRequest);
        verify(messageRequest, times(1)).getMessage();
        verify(messageRequest, times(1)).getChat();
        verify(chatRepository, times(1)).save(any(Message.class));
        verify(socketIONamespace, times(1)).getBroadcastOperations();
        verify(broadcastOperations, times(1)).sendEvent(eq("message-event"), any(Message.class));
    }
}