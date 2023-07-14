package com.sorter;


public class Main {
	public static void main(String[] args) {
		Sortable[] srts = new Sortable[5];
		srts[0] = new Employee(1,"E1",40.5,"d1");
		srts[1] = new Employee(2,"E2",80.5,"d2");
		srts[2] = new Employee(3,"E3",50.2,"d3");
		srts[3] = new Employee(4,"E4",20.8,"d4");
		srts[4] = new Employee(5,"E5",90.4,"d5");
		
		for(Sortable srt: srts) {
			srt.print();
		}
		
		srts[0].sort(srts);
		
		for(Sortable srt: srts) {
			srt.print();
		}
	}
}
