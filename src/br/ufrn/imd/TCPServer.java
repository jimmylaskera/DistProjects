package br.ufrn.imd;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String args[]){
        ServerSocket server = null;
        try {
            server = new ServerSocket(8080);
        } catch (IOException e2) {
            e2.printStackTrace();
        }

        while(true)
        {
            try {
                Socket nextClient = server.accept(); 
                ObjectInputStream input = new ObjectInputStream(nextClient.getInputStream());
                MsgTCP msg = (MsgTCP)input.readObject();
                System.out.println("Proc:"+msg.getProc()+" Mem:"+msg.getMem()+" Num. Cli:"+msg.getNclients()+" host:"+msg.getHostname());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                try {
                    server.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
