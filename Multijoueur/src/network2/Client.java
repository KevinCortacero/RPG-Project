package network2;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;



public class Client {

	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Personnage perso;

	public Client(String pseudo) throws UnknownHostException, IOException{
		this.socket = new Socket(InetAddress.getLocalHost()/*.getByName("83.205.72.80")*/,26964);
		System.out.println("Connexion établie.");
		this.perso = new Personnage(pseudo);
		this.out = new ObjectOutputStream(this.socket.getOutputStream());
	}

	public void sendPersonnage(){
		try {
			this.out.writeObject(this.perso);
			this.out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deconnexion(){
		try {
			this.socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "[CLIENT : " + this.perso.pseudo + "] : ";
	}
	
	public static void main(String[] arg0) {

		Client twarz;
		try {
			twarz = new Client("Twarz");
			for ( int i = 0; i<4 ; i++);{
				twarz.sendPersonnage();
			}
			twarz.deconnexion();
			
		} catch (UnknownHostException e) {
			System.out.println("Impossible de se connecter, adresse inconnue !");
		} catch (IOException e) {
			System.out.println("Connexion non établie");
		}		
	}
}
