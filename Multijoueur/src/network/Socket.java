package network;

import java.net.DatagramSocket;
import java.net.SocketException;

public class Socket extends DatagramSocket{

	public Socket() throws SocketException {
		super(1042);
		System.out.println("t'as vu ça marche ! connexion au port 1042");
	}
	
	
	
}
