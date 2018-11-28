package com.westos.chat.v4.commands;

import com.westos.chat.v4.Server;

import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class LoginResp implements Serializable, Command {

    private String message;

    public String getMessage() {
        return message;
    }

    public LoginResp(String message) {
        this.message = message;
    }

    @Override
    public void handle(Server server, ConcurrentHashMap<Socket, String> sockets, Socket socket, OutputStream out) {
        System.out.println(message);
        System.out.println("【查看聊天室】:2");
        System.out.println("【群聊】:3 消息");
        System.out.println("【私聊】:4 目标 消息");
    }

    @Override
    public int command() {
        return 5;
    }
}
