package com.westos.chat.v4;

import com.westos.chat.v4.commands.*;

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
        send(socket.getOutputStream(), new LoginReq(nickName));

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
    protected void handle(Socket socket, OutputStream out, Command command) throws IOException {
        command.handle(null, null, socket, out);
    }


    protected void input(Scanner scanner, OutputStream out) {
        try {
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                char cmd = str.charAt(0);
                switch (cmd) {
                    case '2':
                        send(out, new MemberReq());
                        break;
                    case '3':
                        send(out, new PublicReq(str.substring(2)));
                        break;
                    case '4':
                        String[] s = str.substring(2).split(" ");
                        String target = s[0];
                        String message = s[1];
                        send(out, new PrivateReq(target, message));
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
