package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server implements Runnable{


	private Socket socket ;
	private boolean running ;

	public Server(){
		try {
			this.socket = new Socket();
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
		new Thread("Receive"){
			public void run(){

				try{
					while(running){
						byte[] data = new byte[1024];
						DatagramPacket packet = new DatagramPacket(data, data.length);
						socket.receive(packet);

						parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
					}
				}catch(IOException e){
					e.printStackTrace();

				}
			}

		}.start();
	}
	
	public void send(byte[] data, InetAddress address, int port){
		new Thread("Send"){
			public void run(){
				try {
					DatagramPacket packet = new DatagramPacket(data, data.length,address,port);
					socket.send(packet);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
	private void parsePacket(byte[] data, InetAddress address, int port) {
		String msg = new String(data);
		System.out.println(msg);
		
		String send = "[SERVER]" + msg;
		
		send(send.getBytes(),address,port);
	}
}
