package com.schuetzeichel;

import java.net.ServerSocket;
import java.util.ArrayList;

/**
 * Created by fabianschuetzeichel on 09.10.2014.
 */
public class Server {

    static final int PORT = 7667;
    private ServerSocket serverSocket;
    private ArrayList<Connection> clientConnections;

    public static void main(String args[]){
        try {
            (new Server()).startServer();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Server(){
        try{
            this.serverSocket = new ServerSocket(Server.PORT);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void startServer(){
        boolean t = true;
        System.out.println("Server started: " + this.serverSocket.getInetAddress() + " with port: " + this.serverSocket.getLocalPort());
        while (t){ //run serverSocket all the time
            try {
                Connection c = new Connection(this.serverSocket.accept());
                c.run();
                this.clientConnections.add(c);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        try{
            this.serverSocket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
