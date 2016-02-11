package network2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.UnknownHostException;



public class Client{

	private SocketClient socket;
	private BufferedReader in;
	private ObjectOutputStream out;
	private Personnage perso;

	public Client(String pseudo) throws UnknownHostException, IOException{
		this.socket = new SocketClient("0.0.0.0",26964);
		this.perso = new Personnage(pseudo);
		this.out = new ObjectOutputStream(this.socket.getOutputStream());
		this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
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
			System.out.println("Déconnexion...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "[CLIENT : " + this.perso.pseudo + "] : ";
	}
	
	public static void main(String[] arg0){

		Client twarz;
		try {
			twarz = new Client("Twarz");
			twarz.sendPersonnage();
			twarz.sendPersonnage();
			String message = twarz.in.readLine();
			System.out.println(message);
			twarz.deconnexion();
		} catch (UnknownHostException e) {
			System.out.println("Impossible de se connecter, adresse inconnue !");
		} catch (IOException e) {
			System.out.println("Erreur : déconnexion...");
		}		
	}
}
