package com.westos.chat.v4.commands;

import com.westos.chat.v4.Server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class PublicResp implements Serializable, Command {
    private String message;

    public String getMessage() {
        return message;
    }

    public PublicResp(String message) {
        this.message = message;
    }

    @Override
    public void handle(Server server, ConcurrentHashMap<Socket, String> sockets, Socket socket, OutputStream out) throws IOException {
        System.out.println(message);
    }

    @Override
    public int command() {
        return 7;
    }
}
