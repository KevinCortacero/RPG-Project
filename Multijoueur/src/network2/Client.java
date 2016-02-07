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
		Personnage perso = new Personnage("Twarz",100,50);


		try {
			// initialisation 
			socket = new Socket(InetAddress.getLocalHost(),2009);	
			System.out.println("Demande de connexion");

			// identification
			//for ( int i = 0; i<2 ; i++);{
				out = new ObjectOutputStream(socket.getOutputStream());
				out.writeObject(perso);
				out.flush();
				out = new ObjectOutputStream(socket.getOutputStream());
				out.writeObject(perso);
				out.flush();
			//}


			/* envoyer les coordonés
			for ( int i = 0; i<2 ; i++);{
				out.println(perso.getPositionX());
				out.flush();
				out.println(perso.getPositionY());
				out.flush();
			}*/
			//fermer connexion
			socket.close();

		}catch (UnknownHostException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
