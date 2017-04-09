package FabulosoServer;

public class Client 
{
private String ipAddres;
private int objetosRecibidos;
private int objetosFallidos;
private int sumaPromedio;
private int cantidad;
private int tiempoPromedio;

public Client (String pipAddres)
{
	ipAddres = pipAddres;
	cantidad =0;
	sumaPromedio=0;
	tiempoPromedio = 0;
	objetosFallidos=0;
	objetosRecibidos=0;
}

public void fallo()
{
	objetosFallidos++;
}
public void recibido()
{
	objetosRecibidos++;
	}

public void addtime(int time)
{
	sumaPromedio+=time;
	cantidad ++;
	tiempoPromedio = sumaPromedio/cantidad;
	
}
public int getTiempoPromedio() {
	return tiempoPromedio;
}


public void setTiempoPromedio(int tiempoPromedio) {
	this.tiempoPromedio = tiempoPromedio;
}



public int getObjetosFallidos() {
	return objetosFallidos;
}
public void setObjetosFallidos(int objetosFallidos) {
	this.objetosFallidos = objetosFallidos;
}
public int getObjetosRecibidos() {
	return objetosRecibidos;
}
public void setObjetosRecibidos(int objetosRecibidos) {
	this.objetosRecibidos = objetosRecibidos;
}
public String getIpAddres() {
	return ipAddres;
}
public void setIpAddres(String ipAddres) {
	this.ipAddres = ipAddres;
}

}
