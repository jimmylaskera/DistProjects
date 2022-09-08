package br.ufrn.imd;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {
	public UDPClient(String msg, int port) {
		System.out.println("Processing requisition...");
		try (Scanner scanner = new Scanner(System.in)) {
			try {
				DatagramSocket clientSocket = new DatagramSocket();
				InetAddress inetAddress = InetAddress.getByName("localhost");
				byte[] sendMessage;
				sendMessage = msg.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(
						sendMessage, sendMessage.length, 
						inetAddress, port);
				clientSocket.send(sendPacket);
				clientSocket.close();
			} catch (IOException ex) { }
		}
		System.out.println("Requisition sent.");
	}
}
