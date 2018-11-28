package com.westos.chat.v3;

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
            byte[] content = new byte[length];
            in.read(content);
            String str = new String(content, "utf-8");
            handle(socket, out, cmd, str);
        }
    }

    protected void send(OutputStream out, int cmd, String content) throws IOException {
        out.write(cmd);
        byte[] bytes = content.getBytes("utf-8");
        int length = bytes.length;
        out.write(0xFF & length >> 8);
        out.write(0xFF & length);
        out.write(bytes);
    }

    protected abstract void handle(Socket socket, OutputStream out, int cmd, String str) throws IOException;
}
