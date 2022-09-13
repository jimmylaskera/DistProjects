package br.ufrn.imd;

public class Safe {
	private float moneyAvailable = 0f;
	private float moneyBundled = 0f;
	
	public Safe () {}
	public Safe (float avl, float bnd) {
		moneyAvailable = avl;
		moneyBundled = bnd;
	}
	
	float getMoneyBundled () { return moneyBundled; }
	void setMoneyBundled (float bnd) { moneyBundled = bnd; }
	
	float getMoneyAvailable () { return moneyAvailable; }
	void setMoneyAvailable (float avl) { moneyAvailable = avl; }

	float getTotal () { return moneyAvailable + moneyBundled; }
}