package FabulosoServer;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import clienteudp.backend.ObjetoUDP;

public class Conexion extends Thread{

private DatagramSocket socket;
private DatagramPacket incoming;

	
	public Conexion(DatagramSocket pSock, DatagramPacket pincoming) 
	{
		socket = pSock;
		incoming= pincoming;
		this.start();  

		// TODO Auto-generated constructor stub
	}

	public void run() { 

		
		
		try
		{
			ByteArrayInputStream bin = new ByteArrayInputStream(incoming.getData());

			ObjectInputStream oin = new ObjectInputStream(bin);
			ObjetoUDP recibido=(ObjetoUDP) oin.readObject();

			oin.close();
			bin.close();
			Date actual =new Date();
			  long diffInMillies = actual.getTime() - recibido.getMarcaTiempo().getTime();
			    long something= TimeUnit.MILLISECONDS.convert(diffInMillies,TimeUnit.MILLISECONDS);
			    
			System.out.println("FROM SERVER: Objeto" + recibido.getNumeroSecuencia());
			System.out.println(recibido.getNumeroSecuencia()+": " + something +" ms");
			System.out.println("Direccion Cliente: "+incoming.getAddress().getHostAddress());
			recibido = null;

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	
	}

}
