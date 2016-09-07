package network2.client;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
	private BufferedReader reader;
	private ObjectOutputStream writter;
	private Player player;
	private ClientFrame frame;

	public Client(String pseudo) {
		try {
			this.socket = new SocketClient("localhost",26964);
			this.player = new Player(pseudo);
			this.player.initialize();
			this.writter = new ObjectOutputStream(this.socket.getOutputStream());
			this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			this.frame = new ClientFrame(pseudo);
			this.addListener();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendPersonnage(){
		try {
			Player p = new Player("TWARZ");
			System.out.println("Client : " + p);
			this.writter.writeObject(p);
			this.writter.flush();
			
			p.setPseudo("KOREUC");
			System.out.println("Client : " + p);
			this.writter.writeObject(p);
			this.writter.flush();
			
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
		return "[CLIENT : " + this.player.getPseudo() + "] : ";
	}

	public static void main(String[] arg0){

		Client koreuc;
		try {
			koreuc = new Client("Koreuc");
			koreuc.sendPersonnage();
			String message = koreuc.reader.readLine();
			System.out.println(message);

		} catch (UnknownHostException e) {
			System.out.println("Impossible de se connecter, adresse inconnue !");
		} catch (IOException e) {
			System.out.println("Erreur : déconnexion...");
		}		
	}

	public void addListener(){
		this.frame.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				
				boolean moved = true;
				
				switch(e.getKeyCode()){
				case 90 : 
					Client.this.player.setPositionY(Client.this.player.getPositionY()-1);
					System.out.println("UP");
				break;
				case 68 : 
					Client.this.player.setPositionX(Client.this.player.getPositionX()+1);
				break;
				case 83 : 
					Client.this.player.setPositionY(Client.this.player.getPositionY()+1);
				break;
				case 81 : 
					Client.this.player.setPositionX(Client.this.player.getPositionX()-1);
				break;
				default :
					moved = false;
				}
				
				if (moved){
					System.out.println("mis a jour : " + Client.this.player);
					Client.this.sendPersonnage();
				}
			}
		});
	}

	public void read(){

	}
}
