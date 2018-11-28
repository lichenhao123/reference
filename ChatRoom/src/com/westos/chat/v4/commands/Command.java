package com.westos.chat.v4.commands;

import com.westos.chat.v4.Server;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public interface Command {

    void handle(Server server, ConcurrentHashMap<Socket, String> sockets, Socket socket, OutputStream out) throws IOException;

    int command();

    static byte[] ser(Command command) throws IOException {
        ByteArrayOutputStream a = new ByteArrayOutputStream(1024);
        ObjectOutputStream b = new ObjectOutputStream(a);
        b.writeObject(command);
        return a.toByteArray();
    }

    static Command deser(byte[] bytes) throws IOException {
        ByteArrayInputStream a = new ByteArrayInputStream(bytes);
        ObjectInputStream b = new ObjectInputStream(a);
        try {
            return (Command) b.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
