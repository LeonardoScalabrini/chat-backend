package com.chat.socket;

import com.chat.config.SocketConfig;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SocketFactory {

    private final SocketConfig socketConfig;

    @Autowired
    public SocketFactory(SocketConfig socketConfig) {
        this.socketConfig = socketConfig;
    }

    public SocketIOServer create() {
        Configuration config = new Configuration();
        config.setHostname(socketConfig.getHostname());
        config.setPort(socketConfig.getPort());

        return new SocketIOServer(config);
    }
}
