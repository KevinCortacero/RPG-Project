package network2;
import ihm.ClientFrame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.UnknownHostException;



public class Client{

	private SocketClient socket;
	private BufferedReader in;
	private ObjectOutputStream out;
	private Player perso;
	private ClientFrame clientFrame;

	public Client(String pseudo) throws UnknownHostException, IOException{
		this.socket = new SocketClient("83.205.72.80",26964);
		this.perso = new Player(pseudo);
		this.out = new ObjectOutputStream(this.socket.getOutputStream());
		this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		this.clientFrame = new ClientFrame(pseudo);
	}

	public void sendPersonnage(){
		try {
			this.out.writeObject(this.perso);
			this.out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deconnexion(){
		try {
			this.out.writeObject(Command.DISCONECT);
			this.socket.close();
			System.out.println("Déconnexion... ");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "[CLIENT : " + this.perso.pseudo + "] : ";
	}
	
	public static void main(String[] arg0){

		try {
			Client twarz = new Client("Twarz");
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

	public String getPseudo() {
		return this.perso.pseudo;
	}

	public SocketClient getSocket() {
		return this.socket;
	}
}
