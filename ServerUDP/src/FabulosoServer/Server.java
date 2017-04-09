package FabulosoServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server   extends Thread{

	private String puerto;
	
	public Server (String pPuerto)
	{	
		puerto = pPuerto;
		this.start();

	}
	public void run()
	{
	 DatagramSocket sock = null;
	         
	        try
	        {
	            //1. creating a server socket, parameter is local port number
	            sock = new DatagramSocket(Integer.parseInt(puerto));
	             
	            //buffer to receive incoming data
	            byte[] buffer = new byte[1024];
	            DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
	             
	            //2. Wait for an incoming data
	         //   echo("Server socket created. Waiting for incoming data...");
	             
	            //communication loop
	            while(true)
	            {
	            	 sock.receive(incoming);
	                Conexion papitas = new Conexion(sock,incoming);
	                
	       
	            }
	        }
	         
	        catch(IOException e)
	        {
	            System.err.println("IOException " + e);
	        }

	
		 

	}
	
    //simple function to echo data to terminal
    public static void echo(String msg)
    {
        System.out.println(msg);
    }
    

	public static void main(String args[])
    {
		String puerto = "5000";
		for (int i = 0; i < args.length; i++) {
			  puerto = args[i];
		}
		  System.out.println("Puerto: "+puerto);

		Server servidor = new Server(puerto);
		
	  
	
    
}

	
	

	
	
}
	
	


