package com.westos.chat.v3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client extends AbstractCS {

    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.start();

    }

    public void start() throws IOException {
        Socket socket = new Socket("localhost", 5000);

        // 功能1： 昵称
        Scanner s = new Scanner(System.in);
        System.out.println("请输入昵称：");
        String nickName = s.nextLine();
        send(socket.getOutputStream(), 1, nickName);

        // 第一个线程负责从控制台获取输入
        new Thread(() -> {
            try (
                    Scanner scanner = new Scanner(System.in);
                    OutputStream out = socket.getOutputStream();
            ) {
                input(scanner, out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        // 第二个线程负责接收服务器端的输入
        new Thread(() -> {
            try (
                    InputStream in = socket.getInputStream();
                    OutputStream out = socket.getOutputStream();
            ) {
                receive(socket, in, out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    protected void handle(Socket socket, OutputStream out, int cmd, String content) throws IOException {
        switch (cmd) {
            case 5:
                System.out.println(content);
                System.out.println("【查看聊天室】:2");
                System.out.println("【群聊】:3 消息");
                System.out.println("【私聊】:4 目标 消息");
                break;
            case 6:
                System.out.println(content);
                break;
            case 7:
                System.out.println(content);
                break;
            case 8:
                System.out.println(content);
                break;
            case 9:
                System.out.println(content);
                break;
            default:
                System.out.println("不支持的命令");
        }
    }


    protected void input(Scanner scanner, OutputStream out) {
        try {
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                char cmd = str.charAt(0);
                switch (cmd) {
                    case '2':
                        send(out, 2, "");
                        break;
                    case '3':
                        send(out, 3, str.substring(2));
                        break;
                    case '4':
                        send(out, 4, str.substring(2));
                        break;
                    default:
                        System.out.println("不支持的命令");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
