package networkRMI;

import java.net.Socket;


public class ConnectedClient {
	
	private Socket socket;
	private PersonnageImpl perso;
	
	public ConnectedClient(Socket socket, PersonnageImpl perso) {
	this.socket = socket;
	this.perso = perso;
	}
		
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public PersonnageImpl getPers() {
		return this.perso;
	}

	public void setPerso(PersonnageImpl perso) {
		this.perso = perso;
	}


}
