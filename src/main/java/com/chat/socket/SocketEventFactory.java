package com.chat.socket;

import com.chat.config.SocketConfig;
import com.chat.models.MessageRequest;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SocketEventFactory {

    private static final String NAME_FORMAT = "/%s";
    private final SocketFactory socketFactory;
    private final SocketConfig socketConfig;

    @Autowired
    public SocketEventFactory(SocketFactory socketFactory, SocketConfig socketConfig) {
        this.socketFactory = socketFactory;
        this.socketConfig = socketConfig;
    }

    public SocketIOServer create() {
        SocketIOServer socketIOServer = socketFactory.create();
        socketConfig.getNamespaces().forEach(namespace -> {
            final String name = String.format(NAME_FORMAT, namespace);
            final SocketIONamespace socketIONamespace = socketIOServer.addNamespace(name);
            socketIONamespace.addEventListener(socketConfig.getMessageEvent(), MessageRequest.class, (client, data, ackRequest) -> socketIONamespace.getBroadcastOperations().sendEvent(socketConfig.getMessageEvent(), data));
        });
        return socketIOServer;
    }
}
