package com.animal;

public class Main {

	public static void main(String[] args) {
		Animal[] animals = new Animal[4];
		animals[0] = new Cat();
		animals[1] = new Cat();
		animals[2] = new Dog();
		animals[3] = new Lion();
		
		for(Animal animal: animals) {
			animal.talk();
			animal.respirate();
			System.out.println();
		}
	}

}
