package frc2168_2013.PIDController.TCPStream;

import java.io.*;
import java.net.*;

class TCPDesktopServer
{
   public static void main(String argv[]) throws Exception
      {
	   
	   	int port = 5789;
        String clientData;

        ServerSocket welcomeSocket = new ServerSocket(port);
         
        System.out.println("Listening on: " + welcomeSocket.getInetAddress().getHostAddress() + ":" + welcomeSocket.getLocalPort());
         
        Socket connectionSocket = welcomeSocket.accept();
        System.out.println("Client Connected");
        

         while(true)
         {
        	 
             
            BufferedReader inFromClient =
               new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            
            clientData = inFromClient.readLine();
            System.out.println("Received: " + clientData);
            
            //If you need to write data back to the client uncomment these lines
//            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
//            String send = "Data to Write to bone";
//            outToClient.writeBytes(send);
//            System.out.println("Sent: " + send);
         }
      }
}