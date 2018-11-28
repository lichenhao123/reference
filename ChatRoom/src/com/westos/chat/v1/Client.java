package com.westos.chat.v1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5000);

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
                receive(in, out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
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
            System.out.println("收到服务器命令==> cmd:" + cmd + " length:" + length);
            byte[] content = new byte[length];
            in.read(content);
            String str = new String(content, "utf-8");
            handle(out, cmd, str);
        }
    }

    protected static void handle(OutputStream out, int cmd, String content) throws IOException{
        switch (cmd) {
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            default:
                System.out.println("不支持的命令");
        }
    }


    protected static void input(Scanner scanner, OutputStream out) {
        try {
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                char cmd = str.charAt(0);
                switch (cmd) {
                    case '2':
                        send(out, 2, "22222");
                        break;
                    case '3':
                        send(out, 3, "你哈");
                        break;
                    case '4':
                        send(out, 4, "88");
                        break;
                    default:
                        System.out.println("不支持的命令");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected static void send(OutputStream out, int cmd, String content) throws IOException {
        out.write(cmd);
        byte[] bytes = content.getBytes("utf-8");
        int length = bytes.length;
        System.out.println("向服务器发送命令==> cmd:" + cmd + " length:" + length);
        out.write(0xFF & length >> 8);
        out.write(0xFF & length);
        out.write(bytes);
    }
}
