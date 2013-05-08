package frc2168_2013.utils;

import java.io.*;
import java.net.*;

/**
 * Created with IntelliJ IDEA.
 * User: Vittorio
 * Date: 5/7/13
 * Time: 8:26 PM
 * To change this template use File | Settings | File Templates.
 */

public class GetVisionData {

    public static double distance = 0;
    public static double rotationAngle = 0;


   public GetVisionData() throws Exception{


       String sentence;
       String modifiedSentence;
       BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
       Socket clientSocket = new Socket("localhost", 5789);
       DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
       BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
       sentence = inFromUser.readLine();
       outToServer.writeBytes(sentence + '\n');
       modifiedSentence = inFromServer.readLine();
       System.out.println("FROM SERVER: " + modifiedSentence);

       String[] values = modifiedSentence.split(",");

       distance = Double.valueOf(values[0]);
       rotationAngle = Double.valueOf(values[1]);

       clientSocket.close();

   }
}
