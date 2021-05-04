package com.chat.commands;

import com.chat.config.SocketConfig;
import com.chat.models.Message;
import com.chat.models.MessageId;
import com.chat.models.MessageRequest;
import com.chat.repositorys.ChatRepository;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.listener.DataListener;

public class SaveMessageCommand implements DataListener<MessageRequest> {

    private final ChatRepository chatRepository;
    private final SocketIONamespace socketIONamespace;
    private final SocketConfig socketConfig;

    public SaveMessageCommand(ChatRepository chatRepository, SocketIONamespace socketIONamespace, SocketConfig socketConfig) {
        this.chatRepository = chatRepository;
        this.socketIONamespace = socketIONamespace;
        this.socketConfig = socketConfig;
    }

    @Override
    public void onData(SocketIOClient socketIOClient, MessageRequest messageRequest, AckRequest ackRequest) {
        Message message = new Message(new MessageId(), messageRequest.getMessage(), messageRequest.getChat());
        chatRepository.save(message);
        socketIONamespace.getBroadcastOperations().sendEvent(socketConfig.getMessageEvent(), message);
    }
}
