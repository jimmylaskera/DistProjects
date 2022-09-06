package br.ufrn.imd;

public class Safe {
	private double packaged;
	private double available;
	
	public Safe () {}
	public Safe (double pck, double avl) {
		this.packaged = pck;
		this.available = avl;
	}
	
	double getPackaged () { return packaged; }
	void setPackaged (double pck) { packaged = pck; }
	
	double getAvailable () { return available; }
	void setAvailable (double avl) { available = avl; }
	
	public static void main(String args[]) {
		new UDPServer("8002");
	}
}
