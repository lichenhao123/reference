package com.westos.chat.v4.commands;

import com.westos.chat.v4.Server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class PublicReq implements Serializable, Command {

    private String message;

    public String getMessage() {
        return message;
    }

    public PublicReq(String message) {
        this.message = message;
    }

    @Override
    public void handle(Server server, ConcurrentHashMap<Socket, String> sockets, Socket socket, OutputStream out) throws IOException {
        sockets.keySet().forEach(s -> {
            String nickname = sockets.get(socket);
            try {
                server.send(s.getOutputStream(), new PublicResp("【" + nickname + "】说:" + message));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public int command() {
        return 3;
    }
}
