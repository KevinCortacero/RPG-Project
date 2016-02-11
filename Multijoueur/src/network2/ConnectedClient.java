package network2;

import java.net.Socket;


public class ConnectedClient {
	
	private Socket socket;
	private Player perso;
	
	public ConnectedClient(Socket socket, Player perso) {
		this.socket = socket;
		this.perso = perso;
	}
		
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public Player getPers() {
		return this.perso;
	}

	public void setPerso(Player perso) {
		this.perso = perso;
	}


}
