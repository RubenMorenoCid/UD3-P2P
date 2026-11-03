package servidorTCP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) {
		/**
		 * cada vez que llega un cliente, asignamos un hilo y espera. Las líneas del
		 * ServerSocket y del accept() son las que sacamos del constructor del profe.
		 */

		int puerto = 6000; // El del ejemplo lo tengo ocupado
		TablaClientes tabla = new TablaClientes();
		try {
			ServerSocket servidor = new ServerSocket(puerto);
			System.out.println("Esperando conexiones en el puerto " + puerto + "...");

			while (true) {
				Socket socket = servidor.accept();
				System.out.println("Conexión aceptada: " + socket);
				HiloCliente hilo = new HiloCliente(new ServidorTCP(socket), tabla);
				hilo.start();
			}
		} catch (IOException e) {
			System.out.println("No puede escuchar en el puerto: " + puerto);
			e.printStackTrace();
		}

	}

}
