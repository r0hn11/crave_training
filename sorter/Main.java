package com.sorter;

public class Main {
	public static void main(String[] args) {
		Sortable[] emps = new Employee[5];
		emps[0] = new Employee(1,"E1",40.5,"d1");
		emps[1] = new Employee(5,"E5",90.4,"d5");
		emps[2] = new Employee(3,"E3",50.2,"d3");
		emps[3] = new Employee(4,"E4",20.8,"d4");
		emps[4] = new Employee(2,"E2",80.5,"d2");
		
		Sortable[] dts = new Date[4];
		dts[0] = new Date(2,5,2001);
		dts[1] = new Date(2,1,2004);
		dts[2] = new Date(4,7,20027);
		dts[3] = new Date(25,9,2014);
		
		for(Sortable emp: emps) {emp.print();}
		for(Sortable dt: dts) {dt.print();}
		
		emps[0].sort(emps);
		dts[0].sort(dts);
		System.out.println();
		for(Sortable emp: emps) {emp.print();}
		for(Sortable dt: dts) {dt.print();}
	}
}
