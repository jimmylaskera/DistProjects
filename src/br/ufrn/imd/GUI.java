package br.ufrn.imd;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Scanner;

public class GUI {
	private DatagramSocket socket;
	private DatagramPacket response;

	public GUI (int port) {
		try {
			socket = new DatagramSocket(port);
			response = new DatagramPacket(new byte[1024], 1024);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void mainMenu () {
		while (true) {
			System.out.println("Escolha a opção desejada:");
			System.out.println("1. Acessar como cliente");
			System.out.println("2. Criar conta");
			System.out.println("3. Acessar como admin");
			System.out.println("0. Sair");

			Scanner opt = new Scanner(System.in);
			switch (Integer.parseInt(opt.nextLine())) {
				case 1:
				System.out.printf("Insira seu id: ");
				clientMenu(Integer.parseInt(opt.nextLine()));
				break;

				case 2:
				System.out.printf("Insira o novo id: ");
				new UDPClient("criar;"+opt.nextLine()+";0;", 8001);
				try {
					socket.receive(response);
					System.out.println(response.getData());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

				case 3:
				adminMenu();
				break;

				case 0:
				opt.close();
				System.exit(0);

				default:
				System.out.println("Por favor escolha uma das opcoes numeradas.");
				break;
			}
		}
	}
	
	// client options: balance, withdraw, deposit, exit
	public void clientMenu (int id) {
		boolean loop = true;
		while (loop) {
			System.out.println("O que deseja fazer hoje?");
			System.out.println("1. Exibir saldo");
			System.out.println("2. Depositar");
			System.out.println("3. Sacar");
			System.out.println("0. Sair");

			Scanner op = new Scanner(System.in);
			switch (Integer.parseInt(op.nextLine())) {
				case 1:
				new UDPClient("saldo;"+id+";0;", 8001);
				try {
					socket.receive(response);
					System.out.println(response.getData());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

				case 2:
				System.out.printf("Digite a quantidade a depositar: ");
				new UDPClient("depot;"+id+";"+op.nextLine()+";", 8001);
				try {
					socket.receive(response);
					System.out.println(response.getData());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

				case 3:
				System.out.printf("Digite a quantidade a sacar: ");
				float value = -(Float.parseFloat(op.nextLine()));
				new UDPClient("depot;"+id+";"+value+";", 8001);
				try {
					socket.receive(response);
					System.out.println(response.getData());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

				case 0:
				op.close();
				loop = false;
				break;

				default:
				System.out.println("Por favor escolha uma das opcoes numeradas.");
				break;
			}
		}
	}
	
	// admin options: retrieve, refill, unpackage, exit
	public void adminMenu () {
		
	}
}
