package network2.serveur;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import network2.Player;

public class UpdatePlayerServer implements Runnable{
	
	private Socket socket;
	
	public UpdatePlayerServer(Socket socket2) {
		this.socket = socket2;
	}
	
	@Override
	public void run() {
		try {
			ObjectInputStream in = new ObjectInputStream(this.socket.getInputStream());
			Player p = (Player)in.readObject();
			Connexion.updatePlayer(p);
			Server.print(p.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
