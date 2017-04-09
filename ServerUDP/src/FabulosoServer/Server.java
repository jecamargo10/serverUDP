package FabulosoServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;

public class Server   extends Thread{

	private String puerto;
	private ArrayList<Client> info;

	
	public Server (String pPuerto)
	{	
		puerto = pPuerto;
		info = new ArrayList<Client>();
		this.start();

	}
	public boolean clientExits(String pCliente)
	{
		for (int i = 0; i < info.size(); i++) 
		{
		if(info.get(i).getIpAddres().equals(pCliente))	
		{
			return true;
			
		}
		}
		return false;
		
	}
	public void addClient(String ip)
	{
		Client e = new Client(ip);
		info.add(e);
	}
	public void addRecibido(String ip,String texto, int something)
	{
		int numero = Integer.parseInt( texto.split(":")[0]);

		for (int i = 0; i < info.size(); i++) {
		Client cliente =info.get(i);
		if(cliente.equals(ip))	
		{
			cliente.recibido();
			cliente.addtime(something);
			int recibidos = cliente.getObjetosRecibidos();
			if (recibidos !=numero )
			{
			int fallidos=	numero-recibidos;
			cliente.setObjetosFallidos(fallidos);
			}

		}	
		}
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
	            	byte [] recibido = new byte[incoming.getLength()];
	            	recibido =  incoming.getData();
	            	String nombre = incoming.getAddress().getHostAddress();
	            	System.out.println(recibido);
	            	Conexion papitas = new Conexion(sock,recibido,this,nombre);
	                
	       
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
	
	


