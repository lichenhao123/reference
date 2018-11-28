package com.westos.chat.v4.commands;

import com.westos.chat.v4.Server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class MemberReq implements Serializable, Command {

    @Override
    public void handle(Server server, ConcurrentHashMap<Socket, String> sockets, Socket socket, OutputStream out) throws IOException {
        server.send(out, new MemberResp("聊天室成员：" + sockets.values().toString()));
    }

    @Override
    public int command() {
        return 2;
    }
}
