package com.schuetzeichel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by fabianschuetzeichel on 09.10.2014.
 */
public class Connection implements Runnable {

    private Socket socket;
    final static String EOC = ":quit:";

    public Connection(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true){
            //communication-part
            try{
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                String line = new String();
                while((line = bufferedReader.readLine())!=Connection.EOC){
                    //verarbeiten
                    System.out.print(line);
                    line+=line;
                    PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()));
                    printWriter.println(line);
                    printWriter.flush();
                }
                this.socket.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
