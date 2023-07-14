package f13;

public class Car extends Vehicle{
	@SuppressWarnings("unused")
	private double engine_cap, avg_price;
	private int seat_count;
	
	public Car(){System.out.println("-car");}
	public Car(int reg_no, String fuel_type, double speed, double engine_cap, double avg_price, int seat_count) {
		super(reg_no, fuel_type, speed);
		this.engine_cap = engine_cap;
		this.avg_price = avg_price;
		this.seat_count = seat_count;
	}
	
	@Override
    public void move() {
		super.move();
    	System.out.print("move-car ");
    }
	
	@Override
	public void getDetails() {
		super.getDetails();
		System.out.println("Engine cap: "+engine_cap+"cc\nAverage price: $"+avg_price+"K\nSeats: "+seat_count);
	}
}
