/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;




public class Server implements Runnable {
    static  String str;
    static  boolean confirm;

    /**
     *
     * @throws Exception
     */
    @Override
    public   void run() {

        try {
            
            ServerSocket listener = new ServerSocket(9991);                       
            
            try {
                while (true) {
                    Socket socket = listener.accept();
                    socket.setTcpNoDelay(true);
                    socket.setKeepAlive(true);
                    System.out.println("Client Connected");
                    try {
                        DataInput printin = new DataInputStream (socket.getInputStream());
                        while (null != ((str = printin.readLine())))
                        {
                            System.out.println(str);
                            GetData.setuid(str);
                            System.out.println(GetData.getuid());
                            //setConfirm(true);
                            break;
                            
                        }
                        
                        break;    
                        
                    } finally {
                        socket.close();
                    }
                }
            } finally {
                listener.close();
            }
            
            
        }   catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
}
