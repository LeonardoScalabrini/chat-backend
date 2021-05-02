package com.chat;

import com.chat.models.ChatObject;
import com.corundumstudio.socketio.*;
import com.corundumstudio.socketio.listener.DataListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(ChatApplication.class, args);

        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(9092);

        final SocketIOServer server = new SocketIOServer(config);
        final SocketIONamespace chat1namespace = server.addNamespace("/chat1");
        chat1namespace.addEventListener("message", ChatObject.class, new DataListener<ChatObject>() {
            @Override
            public void onData(SocketIOClient client, ChatObject data, AckRequest ackRequest) {
                System.out.println(data.getMessage());
                chat1namespace.getBroadcastOperations().sendEvent("message", data);
            }
        });

        final SocketIONamespace chat2namespace = server.addNamespace("/chat2");
        chat2namespace.addEventListener("message", ChatObject.class, new DataListener<ChatObject>() {
            @Override
            public void onData(SocketIOClient client, ChatObject data, AckRequest ackRequest) {
                System.out.println(data.getMessage());
                chat2namespace.getBroadcastOperations().sendEvent("message", data);
            }
        });

        server.start();

        Thread.sleep(Integer.MAX_VALUE);

        server.stop();
    }

}
