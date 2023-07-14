package com.animal;

abstract public class Animal {
	public Animal() {System.out.println("-noargs-animal");}
	
	// abstract
	public abstract void talk();
	// concrete
	public void respirate() {System.out.println("Breathing");}
}
