package com.hashset;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		HashSet h = new HashSet();
		h.add(new Account(1,1100));
		h.add(new Account(2,5400));
		h.add(new Account(3,2300));
		
		System.out.println(h);
	}

}
