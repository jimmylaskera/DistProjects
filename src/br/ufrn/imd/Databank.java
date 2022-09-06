package br.ufrn.imd;

public class Databank {
	private int id;
	private double balance;
	
	public Databank () {}
	public Databank (int id, float balance) {
		this.id = id;
		this.balance = balance;
	}
	
	int getId () { return id; }
	
	double getBalance () { return balance; }
	void setBalance (double value) { this.balance = value; }
	
	public static void main(String args[]) {
		new UDPServer("8001");
	}
}
