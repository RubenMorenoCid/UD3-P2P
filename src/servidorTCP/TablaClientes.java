package servidorTCP;

import java.util.HashMap;

public class TablaClientes {
	
	private HashMap<String, ClienteInfo> clientes = new HashMap<String,ClienteInfo>();
	
	
	//Lleva synchronized porque lee la tabla(el hasmap), y otro hilo podría estar cambiándola a la vez.
	public synchronized void mostrarClientes() {
		System.out.println("Clientes conectados: " + clientes.size());
		for (String nombre : clientes.keySet()) {
			System.out.println(nombre + " :" + clientes.get(nombre));
			
		}
		
	}
	
	public synchronized void registrar(String nombre, ClienteInfo info) {
		clientes.put(nombre, info); //Guarda en la tabla
		mostrarClientes();
	}
	
	public synchronized void borrar(String nombre) {
		clientes.remove(nombre);
		mostrarClientes();
	}
	
	//Cuando un cliente manda LIST, el servidor le contesta quién está conectado
	public synchronized String listar() {
		return  "Clientes: " + clientes.keySet();
		
	}

}
