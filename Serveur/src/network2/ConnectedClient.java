package network2;

import java.net.Socket;

import network2.client.SocketClient;


public class ConnectedClient {
	
	private Socket socket;
	private Player perso;
	
	public ConnectedClient(Socket socketPlayer, Player perso) {
		this.socket = socketPlayer;
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
