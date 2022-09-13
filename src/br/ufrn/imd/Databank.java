package br.ufrn.imd;

import java.util.HashMap;

public class Databank {
	private HashMap<Integer, Float> accounts = new HashMap<>();

	public Databank() {}
	public Databank(int id, float balance) {
		accounts.put(id, balance);
	}

	public boolean contains(int id) { return accounts.containsKey(id); }
	public void createAcc(int id) { accounts.put(id, 0f); }
	public float getBalance(int id) { return accounts.get(id); }
	public void deposit(int id, float value) { accounts.put(id, accounts.get(id) + value); }
}
