package FabulosoServer;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import clienteudp.backend.ObjetoUDP;

public class Conexion extends Thread{

private DatagramSocket socket;
byte []  llegan;
private Server superServer;
String direccion;
	public Conexion(DatagramSocket pSock, byte[] recibido, Server server, String nombre) 
	{
		socket = pSock;
		llegan= recibido;
		superServer=server;
		direccion= nombre;
		
		this.start();  

		// TODO Auto-generated constructor stub
	}

	public void run() { 

		
		
		try
		{

			System.out.println("LLEGA ALGO");
			ByteArrayInputStream bin = new ByteArrayInputStream(llegan);
			ObjectInputStream oin = new ObjectInputStream(bin);
			ObjetoUDP recibido=(ObjetoUDP) oin.readObject();

			oin.close();
			bin.close();
			Date actual =new Date();
			long diffInMillies = actual.getTime() - recibido.getMarcaTiempo().getTime();
			long something= TimeUnit.MILLISECONDS.convert(diffInMillies,TimeUnit.MILLISECONDS);
			String texto = recibido.getNumeroSecuencia()+": " + something +" ms";
			superServer.addRecibido(direccion,texto,(int) something,recibido.getNumeroTotal());
			recibido = null;


		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	
	}

}
