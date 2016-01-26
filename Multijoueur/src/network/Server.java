package network;

import java.net.DatagramPacket;
import java.net.SocketException;

public class Server implements Runnable{

	private SocketOut socketOut ;
	private boolean running ;
	
	public Server(){
		try {
			this.socketOut = new SocketOut();
		} catch (SocketException e) {
			System.out.println("Problème avec le port");
			e.printStackTrace();
			
		}
		running = true;
		new Thread(this,"Serveur").start();
	}

	@Override
	public void run() {
		System.out.println("Server started");
		receive();
		while(this.running){
			System.out.println("Lol");
		}
		
	}

	private void receive() {
		while(this.running){
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			
		}
	}
}
