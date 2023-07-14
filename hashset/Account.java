package com.hashset;

public class Account{
	private int accId;
	private double balance;
	
	public Account(){System.out.println("no args");}
	public Account(int accId, double balance){
		this.accId = accId;
		this.balance = balance;
	}
	public void showData() {System.out.println(this.balance);}
	@Override
	public String toString() {
		return ("Account: "+this.accId+"-"+this.balance);
	}
}