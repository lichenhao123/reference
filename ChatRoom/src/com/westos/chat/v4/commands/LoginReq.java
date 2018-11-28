package com.westos.chat.v4.commands;

import com.westos.chat.v4.Server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class LoginReq implements Serializable, Command {

    private String nickname;

    public String getNickname() {
        return nickname;
    }

    public LoginReq(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public void handle(Server server, ConcurrentHashMap<Socket, String> sockets, Socket socket, OutputStream out) throws IOException {
        sockets.put(socket, nickname);
        server.send(out, new LoginResp("欢迎来到畅聊【" + nickname + "】"));
    }

    @Override
    public int command() {
        return 1;
    }
}
