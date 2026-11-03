package servidorTCP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

class ServidorTCP {
	private Socket socketCliente;
	private BufferedReader entrada;
	private PrintWriter salida;

	public ServidorTCP(Socket socketCliente) {
		this.socketCliente = socketCliente;

		try {
			entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
			salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream())), true);

		} catch (IOException e) {
			System.out.println("No puede establecer la conexion con el cliente");
			System.exit(-1);
		}
	}

	// Copiados de ecoServidor. No cerramos socketServidor.close(), porque esa
	// centralita debe seguir abierta para los demás clientes. Recibo msg en el serv y envio a otros clientes

	public String recibirMsg() {
		String linea = "";
		try {
			linea = entrada.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return linea;
	}

	public void enviarMsg(String linea) {
		salida.println(linea);
	}
	
	public String getIpCliente() {
		return socketCliente.getInetAddress().getHostAddress();
	}

	public void closeServidorTCP() {
		try {
			salida.close();
			entrada.close();
			socketCliente.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("-> Cliente desconectado");
	}

}
