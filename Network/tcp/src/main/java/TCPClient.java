package client;

import java.net.*;
import java.io.*;

public class TCPClient {
	public static void main (String args[]) {
		// arguments supply message and hostname
		Socket s = null;
		//1) fetch client params (localhost, port, message)
        if (args.length != 3) {
          System.out.println("Wrong number of arguments:\ngradle runClient --args=\"localhost port message\"");
          System.exit(0);
        }
        String host = args[0];
        String message = args[2];
        int portNo = 9099; // default port
        try {
            portNo = Integer.parseInt(args[1]);
        } catch (NumberFormatException nfe) {
            System.out.println("port must be integer");
            System.exit(2);
        }
        //2) create a socket using tcp
		try{
			s = new Socket(host, portNo);    
			DataInputStream in = new DataInputStream( s.getInputStream());
			//4) establish connection to server
			DataOutputStream out =new DataOutputStream( s.getOutputStream());
			//5) send message
			out.writeUTF(message);      	// UTF is a string encoding 
			//6) receive message
			String data = in.readUTF();	    // read a line of data from the stream
			System.out.println("Received: "+ data) ; 
		} catch (UnknownHostException e) {
			System.out.println("Socket:"+e.getMessage());
		} catch (EOFException e) {
			System.out.println("EOF:"+e.getMessage());
		} catch (IOException e) { 
			System.out.println("readline:"+e.getMessage());
		} finally {
			if(s!=null) 
				try {
					//8) close the socket
					s.close();
				} catch (IOException e) {
					System.out.println("close:"+e.getMessage());
				}
			}
	}
}
