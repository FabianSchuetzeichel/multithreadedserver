package com.schuetzeichel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by fabianschuetzeichel on 09.10.2014.
 */
public class Client {

    private Socket socket;

    public static void main(String args[]){
        (new Client()).startClient();
    }

    public Client(){
        try{
            this.socket = new Socket("localhost", Server.PORT);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void startClient(){
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()));

            System.out.println("Client connected: " + this.socket.getInetAddress());
            Scanner scanner = new Scanner(System.in);
            String line = new String();
            while ((line = scanner.nextLine())!=null){
                printWriter.println(line);
                printWriter.flush();
                System.out.println("asd");
                System.out.println(bufferedReader.readLine());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
