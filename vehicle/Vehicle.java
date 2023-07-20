package f13;

public class Vehicle {
	public int reg_no;
	public String fuel_type;
	public double speed;
	
	public Vehicle(){System.out.println("-vehicle");}
	public Vehicle(int reg_no, String fuel_type, double speed){
		this.reg_no = reg_no;
		this.fuel_type = fuel_type;
		this.speed = speed;
	}
	
	public void move(){System.out.print("move-vehicle ");}
	public void getDetails() {
		System.out.println("\nRegistration no.: "+reg_no+"\nFuel type: "+fuel_type+"\nspeed: "+speed);
	}
}
