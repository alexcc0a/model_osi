package ru.netology;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        System.out.println("Запуск сервера");
        int port = 3428;

        String adult = "Добро пожаловать, %s вы можете записаться на бесплатный видинар и выбрать свою будущую професию!"
                + " Пройдите по сссылке что бы узнать больше https://netology.ru";
        String child = "Отлично, Добро пожаловать!, %s Продолжайте свое обучение, желаем вам успехов!";

        while (true) {
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                out.println("Здравствуйте, напишите ваше имя");
                String nameClient = in.readLine();
                out.println("Вы обучаетесь в Нетологии? (да/нет)");
                String childYesOrNo = in.readLine();

                switch (childYesOrNo){
                    case "да":
                        out.println(String.format(child,nameClient));
                        break;
                    case "нет" :
                        out.println(String.format(adult,nameClient));
                        break;
                    default:
                        out.println("Некорректные данные");
                }

                System.out.printf("New connection accepted. Port: %d%n",clientSocket.getPort());
                final String name = in.readLine();
                out.println(String.format("Hi %s, your port is %d",name,clientSocket.getPort()));
                serverSocket.close();

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}