package com.list;

import java.util.*;

public class Cities {
	public static void main(String[] args) {
		List cities = new ArrayList();
		cities.add("ct1");
		cities.add("ct2");
		cities.add("ct3");
		cities.add("ct4");
		
		for(Object ct: cities) {
			String ctst = (String) ct;
			System.out.println(ctst.length());		
		}
		
		Iterator i = cities.iterator();
		while(i.hasNext()) {
			System.out.println(((String)i.next()).length());
		}
	}
}
