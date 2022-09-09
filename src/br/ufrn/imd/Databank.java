package br.ufrn.imd;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.List;

public class Databank {
	private int id;
	private double balance;
	
	public Databank () {}
	public Databank (int id, double balance) {
		this.id = id;
		this.balance = balance;
	}
	
	int getId () { return id; }
	
	double getBalance () { return balance; }
	void setBalance (double value) { this.balance = value; }
	
	public static void main(String args[]) {
		List<Databank> data = new ArrayList<>();
		data.add(new Databank(1111, 500));
		data.add(new Databank(2222, 2489.63));
		data.add(new Databank(3333, 0.49));
		data.add(new Databank(4444, 2345.9));
		data.add(new Databank(5555, 1349));
		System.out.println("Database UDP Server Starting");

		try {
			try (DatagramSocket serverSocket = new DatagramSocket(8001)) {
				while (true) {
					byte[] receiveMessage = new byte[1024];
					DatagramPacket receivePacket = new DatagramPacket(
							receiveMessage, receiveMessage.length);
					serverSocket.receive(receivePacket);
					String message = new String(receivePacket.getData());
					String action = message.substring(0, message.indexOf(':'));
					String content = message.substring(message.indexOf(':')+1);
					switch (action) {
						case "auth":
							String response = "auth:";
							for (Databank d: data) {
								if (d.getId() == Integer.parseInt(content)) {
									response += "yes";
									break;
								}
							}
							if (response.equals("auth:")) response += "no";
							receivePacket.setData(response.getBytes());
							serverSocket.send(receivePacket);
							break;
					}
				}
			}
		} catch (IOException e) {e.printStackTrace();}
	}
}
