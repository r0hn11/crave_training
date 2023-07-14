package com.root;

public class NegativeNumberException extends Exception{
	@Override
	public String toString() {
		return ("Negative number square root can't be found");
	}
}
