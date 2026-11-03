package servidorTCP;

import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		System.out.println("Indica el nombre:");
		String nombre = in.nextLine();
		System.out.println("Indica el puerto:");
		String puerto = in.nextLine();
		System.out.println("Indica los archivos:");// sin espacios, con coma (,)
		String archivos = in.nextLine();

		ClienteTCP canal = new ClienteTCP("localhost", 6000);

		canal.enviarMsg("REGISTER " + nombre + " " + puerto + " " + archivos);
		System.out.println("Servidor: " + canal.recibirMsg());

		// Mismo do-while que EcoCliente del profe: leo, envío y muestro la respuesta
		// hasta que el usuario se va.
		// UNREGISTER necesita el nombre según el protocolo, así que lo añade el
		// programa.

		String linea;
		do {
			System.out.println("Comando (LIST o UNREGISTER):");
			linea = in.nextLine();

			if (linea.equals("UNREGISTER")) {
				canal.enviarMsg("UNREGISTER " + nombre);
			} else {
				canal.enviarMsg(linea);
			}
			System.out.println("Servidor: " + canal.recibirMsg());
		} while (!linea.equals("UNREGISTER"));

		canal.closeClienteTCP(); // Cierro conexion con el cliente, el servidor sigue funcionando con los ortros clinetes
	}

}
