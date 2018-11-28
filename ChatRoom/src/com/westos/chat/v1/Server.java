package com.westos.chat.v1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            while (true) {
                // 来一个连接，开一个空闲线程处理请求，但上限是10
                Socket socket = serverSocket.accept();
                service.submit(() -> {
                    try (
                            InputStream in = socket.getInputStream();
                            OutputStream out = socket.getOutputStream();
                    ) {
                        receive(in, out);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static void receive(InputStream in, OutputStream out) throws IOException {
        while (true) {
            int cmd = in.read();
            if (cmd == -1) {
                break;
            }
            int hi = in.read();
            int lo = in.read();
            int length = (hi << 8) + lo;
            System.out.println("收到客户端命令==> cmd:" + cmd + " length:" + length);
            byte[] content = new byte[length];
            in.read(content);
            String str = new String(content, "utf-8");
            handle(out, cmd, str);
        }
    }

    protected static void handle(OutputStream out, int cmd, String content) throws IOException{
        switch (cmd) {
            case 1:
                send(out, 5, content);
                break;
            case 2:
                send(out, 6, content);
                break;
            case 3:
                send(out, 7, content);
                break;
            case 4:
                send(out, 8, content);
                break;
            default:
                System.out.println("不支持的命令");
        }
    }

    protected static void send(OutputStream out, int cmd, String content) throws IOException {
        out.write(cmd);
        byte[] bytes = content.getBytes("utf-8");
        int length = bytes.length;
        System.out.println("向客户端发送命令==> cmd:" + cmd + " length:" + length);
        out.write(0xFF & length >> 8);
        out.write(0xFF & length);
        out.write(bytes);
    }
}
