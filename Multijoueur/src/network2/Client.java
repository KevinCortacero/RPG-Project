package network2;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;



public class Client {

	public static void main(String[] zero) {

		Socket socket;
		ObjectInputStream in;
		ObjectOutputStream out;
		Personnage perso = new Personnage("Vavales",8,8);

		try {
			// initialisation 
			socket = new Socket(InetAddress.getByName("83.205.72.80"),26964);	
			System.out.println("Demande de connexion");

			// identification
			for ( int i = 0; i<5 ; i++);{
				out = new ObjectOutputStream(socket.getOutputStream());
				out.writeObject(perso);
				out.flush();
			}
			socket.close();

		}catch (UnknownHostException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
