/*4. De manera an�loga al ejemplo visto en teor�a, crea una clase servidor que cada vez que se 
conecte un cliente, le env�e un objeto del tipo que has creado en el ejercicio anterior para 
que el cliente lo rellene. Una vez recibido el objeto, lo deber� mostrar por pantalla. Es 
importante que te detengas en ver qu� hace cada una de las instrucciones que est�s utilizando.
Como no podr�s testear el servidor hasta que no tengas el cliente preparado, pasa al 
siguiente ejercicio una vez lo tengas listo.*/

package es.florida.ejercicio04;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
		
		int numeroPuerto = 5000;		//definimos un puerto
		ServerSocket servidor = new ServerSocket(numeroPuerto); //Creamos el objeto servidor con el puerto que hemos definido
		System.err.println("SERVIDOR >>> escuchando...");	
		
		//creamos un objeto Socket asociado al cliente. El servidor se detiene hasta que 
		//haya una conexion de un cliente. Recoger� el objeto Socket del cliente y ya podemos 
		//establecer la comunicaci�n entre los dos
		Socket cliente = servidor.accept();	
		Thread.sleep(2000);
		
		//Mandar objeto al cliente.
		//Una vez recibimos la conexi�n definimos que primero env�a el servidor
		ObjectOutputStream outObjeto = new ObjectOutputStream(cliente.getOutputStream());
		Productos producto = new Productos("Producto", 0);		//definimos objeto a enviar
		outObjeto.writeObject(producto);						//enviamos al cliente cone esta instrucci�n y lo recibir� el cliente
		System.err.println("SERVIDOR >>> Env�o a clienet: " + producto.getProducto() + " - " + producto.getPrecio());
		
		//Recibimos del cliente.
		//Servidor se queda en espera hasta recibir de nuevo el objeto del cliente
		ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream());	//Recibe un objeto con la informaci�n del cliente
		Productos productoMod = (Productos) inObjeto.readObject();	//Creamos un objeto nuevo para meter la info que nos devuelve el cliente con get.InputStream() /readObject() lee el objeto que nos han pasado, hay que castearlo
		Thread.sleep(2000);
		System.err.println("SERVIDOR >>> Recibido de cliente: " + productoMod.getProducto() + " - " + productoMod.getPrecio());
		
		//Cerramos objetos que hemos creado y el servidor y cliente
		outObjeto.close();
		inObjeto.close();
		cliente.close();
		servidor.close();
	}
}
