package com.westos.chat.v4.commands;

import com.westos.chat.v4.Server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class PrivateReq implements Serializable, Command {

    private String message;
    private String target;

    public PrivateReq(String target, String message) {
        this.message = message;
        this.target = target;
    }

    public String getMessage() {
        return message;
    }

    public String getTarget() {
        return target;
    }

    @Override
    public void handle(Server server, ConcurrentHashMap<Socket, String> sockets, Socket socket, OutputStream out) throws IOException {
        Optional<Map.Entry<Socket, String>> first = sockets.entrySet().stream().filter((e) -> e.getValue().equals(target)).findFirst();
        if (first.isPresent()) {
            server.send(first.get().getKey().getOutputStream(), new PrivateResp("【" + sockets.get(socket) + "】说:" + message));
        } else {
            server.send(out, new PrivateResp("该用户不存在"));
        }
    }

    @Override
    public int command() {
        return 4;
    }
}
