package com.westos.chat.v2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server extends AbstractCS {
    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    public void start() {
        System.out.println("==== 【畅聊】聊天室，版本 v2");
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
                        this.receive(socket, in, out);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void handle(Socket socket, OutputStream out, int cmd, String content) throws IOException {
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
}
