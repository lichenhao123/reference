package com.westos.chat.v3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Optional;
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
        System.out.println("==== 【畅聊】聊天室，版本 v3");
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
                                send(s.getOutputStream(), 9, "【" + nickname + "】离开了聊天室");
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
    protected void handle(Socket socket, OutputStream out, int cmd, String content) throws IOException {
        switch (cmd) {
            case 1:
                // 这时候content就是昵称
                sockets.put(socket, content);
                send(out, 5, "欢迎来到畅聊【" + content + "】");
                break;
            case 2:
                send(out, 6, "聊天室成员：" + sockets.values().toString());
                break;
            case 3:
                sockets.keySet().forEach(s -> {
                    String nickname = sockets.get(socket);
                    try {
                        send(s.getOutputStream(), 7, "【" + nickname + "】说:" + content);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                break;
            case 4:
                String[] array = content.split(" ");
                String name = array[0];
                String c = array[1];
                Optional<Map.Entry<Socket, String>> first = sockets.entrySet().stream().filter((e) -> e.getValue().equals(name)).findFirst();
                if (first.isPresent()) {
                    send(first.get().getKey().getOutputStream(), 8, "【" + sockets.get(socket) + "】说:" + c);
                } else {
                    send(out, 8, "该用户不存在");
                }
                break;
            default:
                System.out.println("不支持的命令");
        }
    }
}
