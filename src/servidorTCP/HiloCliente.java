package servidorTCP;

import java.util.ArrayList;

public class HiloCliente extends Thread {

	private ServidorTCP canal;
	private TablaClientes tabla;
	private String ip;

	public HiloCliente(ServidorTCP canal, TablaClientes tabla) {
		this.canal = canal;
		this.tabla = tabla;
		this.ip = canal.getIpCliente();
	}

	private void gestionarComando(String linea) {
		String[] partes = linea.split(" ");
		String comando = partes[0];

		if (comando.equals("REGISTER")) {
			String nombre = partes[1];
			int puertoUDP = Integer.parseInt(partes[2]);

			ArrayList<String> archivos = new ArrayList<String>();
			for (String archivo : partes[3].split(",")) {
				archivos.add(archivo);
			}

			tabla.registrar(nombre, new ClienteInfo(ip, puertoUDP, archivos));
			canal.enviarMsg("OK " + nombre + " registrado");

		} else if (comando.equals("LIST")) {
			canal.enviarMsg(tabla.listar());

		} else if (comando.equals("UNREGISTER")) {
			tabla.borrar(partes[1]);
			canal.enviarMsg("OK " + partes[1] + " desconectado");
		} else {
			canal.enviarMsg("ERROR comando desconocido: " + comando);
		}
	}
	// Hilo: escuchar al cliente, atender lo que pide y repetir hasta
	// que se vaya. Mismo del profe

	@Override
	public void run() {
		String linea;
		do {
			linea = canal.recibirMsg();
			if (linea != null) {
				gestionarComando(linea);
			}
		} while (linea != null && !linea.startsWith("UNREGISTER"));
		canal.closeServidorTCP();

	}

}
