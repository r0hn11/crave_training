package com.sorter;

public class Employee implements Sortable{
	private int empId;
	private String name;
	private double salary;
	private String designation;
	
	public Employee(int empId, String name, double salary, String designation) {
		this.empId = empId;
		this.name = name;
		this.salary = salary;
		this.designation = designation;
	}
	
	@Override
	public void print() {
		System.out.println("Salary: "+salary+"K");		
	}
	
	@Override
	public void sort(Sortable[] s) {
		s = (Employee[]) s;
		Employee tmp;
		for(int i=0; i < s.length; i++) {
			for(int j=1; j < (s.length-i); j++) {
				if(s[j-1].salary>s[j].salary) {
					tmp = s[j-1];
					s[j-1] = s[j];
					s[j] = tmp;
				}
			}
		}
	}
}
