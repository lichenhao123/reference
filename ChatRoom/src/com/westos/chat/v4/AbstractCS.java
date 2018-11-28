package com.westos.chat.v4;

import com.westos.chat.v4.commands.Command;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public abstract class AbstractCS {

    protected void receive(Socket socket, InputStream in, OutputStream out) throws IOException {
        while (true) {
            int cmd = in.read();
            if (cmd == -1) {
                break;
            }
            int hi = in.read();
            int lo = in.read();
            int length = (hi << 8) + lo;
            byte[] bytes = new byte[length];
            in.read(bytes);
            Command command = Command.deser(bytes);
            handle(socket, out, command);
        }
    }

    public void send(OutputStream out, Command cmd) throws IOException {
        out.write(cmd.command());
        byte[] bytes = Command.ser(cmd);
        int length = bytes.length;
        out.write(0xFF & length >> 8);
        out.write(0xFF & length);
        out.write(bytes);
    }

    protected abstract void handle(Socket socket, OutputStream out, Command command) throws IOException;
}
