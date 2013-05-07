import java.io.*;
import java.net.*;

class TCPClient
{
  private static Distance = 0;
	private static RotationAngle = 0; 
	
 public TCPClinet() throws Exception
 {
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
  
  Distance = modifiedSentence.split(",")(0);
  RotationAngle = modifiedSentence.split(",")(1);
  
  clientSocket.close();
 }
}
