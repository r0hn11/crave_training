package com.root;

public class Main {
	public static void main(String[] args) {
		try {
			double s = sqrRoot(args[0]);
			System.out.println(s);
		}
		catch(NegativeNumberException e) {
			e.printStackTrace();
		}
	}
	
	public static double sqrRoot(String n)  throws NegativeNumberException{
		int n2 = Integer.parseInt(n);
		if(n2<0) throw new NegativeNumberException();
		else return Math.sqrt(n2);
	}
}
