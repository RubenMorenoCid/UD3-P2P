package servidorTCP;

import java.util.ArrayList;

public class ClienteInfo {
	
	// Cremamos una ficha de datos
	
	protected String ip;
	protected int puertoUDP;
	protected ArrayList<String> archivos;
	
	public ClienteInfo(String id, int puertoUDP, ArrayList<String> archivos) {
		
		this.ip = id;
		this.puertoUDP = puertoUDP;
		this.archivos = archivos;
	}

	@Override
	public String toString() {
		return ip + " :" + puertoUDP + "--" + archivos;
	}
	
	
	
	


}
