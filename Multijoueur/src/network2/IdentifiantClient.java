package network2;

import java.net.Socket;


public class IdentifiantClient {
	
	private Socket socket;
	private Personnage perso;
	
	public IdentifiantClient(Socket socket, Personnage perso) {
	this.socket = socket;
	this.perso = perso;
	}
		
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public Personnage getPers() {
		return this.perso;
	}

	public void setPerso(Personnage perso) {
		this.perso = perso;
	}


}
