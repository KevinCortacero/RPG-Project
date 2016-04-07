package network2.client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import ihm.ClientFrame;
import network2.Player;

public class Client{

	private Socket socket;
	private BufferedReader in;
	private ObjectOutputStream out;
	private Player perso;
	private ClientFrame frame;

	public Client(String pseudo) {
		try {
			this.socket = new SocketClient("90.5.58.98",26964);
			this.perso = new Player(pseudo);
			this.out = new ObjectOutputStream(this.socket.getOutputStream());
			this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			this.frame = new ClientFrame(pseudo);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
			this.socket.close();
			System.out.println("Déconnexion...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "[CLIENT : " + this.perso.getPseudo() + "] : ";
	}

	public static void main(String[] arg0){

		Client twarz;
		try {
			twarz = new Client("Twarz");
			twarz.sendPersonnage();
			System.out.println("test1");
			String message = twarz.in.readLine();
			System.out.println(message);

			// UpdatePlayer
			new Thread(){
				public void run(){
					ObjectOutputStream out;
					int nb = 0 ; // nombre de fois que j'envoie mon player
					while(nb < 2 ){
						twarz.sendPersonnage();				
						//try {this.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();} // dodo
						nb++;
					}
				}
			};
			twarz.deconnexion();
		} catch (UnknownHostException e) {
			System.out.println("Impossible de se connecter, adresse inconnue !");
		} catch (IOException e) {
			System.out.println("Erreur : déconnexion...");
		}		
	}
}
