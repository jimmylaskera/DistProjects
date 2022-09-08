package br.ufrn.imd;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Scanner;

public class GUI {
	
	// checks card id to redirect to client/admin menu
	public static void cardInsertMenu () {
		while (true) {
			System.out.printf("Insert a card id: ");
			try (Scanner input = new Scanner(System.in)) {
				while (input.hasNextLine()) {
					String msg = "auth:" + input.nextLine();
					new UDPClient(msg, 8001);
				}
			}
			System.out.println("Verifying...");
			try {
				try (DatagramSocket serverSocket = new DatagramSocket(8000)) {
					byte[] receiveMessage = new byte[1024];
					DatagramPacket receivePacket = new DatagramPacket(
						receiveMessage, receiveMessage.length);
					serverSocket.receive(receivePacket);
					String message = new String(receivePacket.getData());
					String action = message.substring(0, message.indexOf(':'));
					String content = message.substring(message.indexOf(':')+1);
					if (action.equals("auth") && content.equals("yes")) {
						serverSocket.close();
						clientMenu();
					} else System.out.println("Id inválido. Tente novamente.");
				}
			} catch (IOException e) {e.printStackTrace();}
			
		}
	}
	
	// client options: balance, withdraw, deposit, exit
	public static void clientMenu () {
		
	}
	
	// admin options: retrieve, refill, unpackage, exit
	public void adminMenu () {
		
	}

	public static void main(String args[]) {
		System.out.println("Interface UDP Server Starting...");
		cardInsertMenu();
	}
}
