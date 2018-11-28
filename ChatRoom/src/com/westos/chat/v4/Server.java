package com.westos.chat.v4;

import com.westos.chat.v4.commands.Command;
import com.westos.chat.v4.commands.LogoutResp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server extends AbstractCS {

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    private ConcurrentHashMap<Socket, String> sockets = new ConcurrentHashMap<>();

    public void start() {
        System.out.println("==== 【畅聊】聊天室，版本 v4");
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
//                        e.printStackTrace();
                        String nickname = sockets.get(socket);
                        sockets.remove(socket);
                        sockets.keySet().forEach(s -> {
                            try {
                                send(s.getOutputStream(), new LogoutResp("【" + nickname + "】离开了聊天室"));
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        });
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void handle(Socket socket, OutputStream out, Command command) throws IOException {
        command.handle(this, sockets, socket, out);
    }

}
