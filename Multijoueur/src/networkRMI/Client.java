package networkRMI;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.RemoteException;



public class Client {

	public static void main(String[] zero) {


		Socket socket;
		ObjectInputStream in;
		ObjectOutputStream out;
		PersonnageImpl perso;
		try {
			perso = new PersonnageImpl("Twarz",666,100);
			// initialisation 
			System.out.println(InetAddress.getLocalHost());
			System.out.println(InetAddress.getByName("193.48.172.170"));

			socket = new Socket(InetAddress.getByName("193.48.172.170"),8228);
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
		}catch (RemoteException e1) {
			e1.printStackTrace();
		}catch (UnknownHostException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}



	}
}
