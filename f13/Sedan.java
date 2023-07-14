package f13;

public class Sedan extends Car{
	private WEIGHT weight;
	private double bootspace_area;
	private boolean has_cng;
	
	public Sedan(int reg_no, String fuel_type, double speed, double engine_cap, double avg_price, int seat_count, WEIGHT weight, double bootspace_area, boolean has_cng) {
		super(reg_no, fuel_type, speed, engine_cap, avg_price, seat_count);
		this.weight = weight;
		this.bootspace_area = bootspace_area;
		this.has_cng = has_cng;
	}
	
	@Override
    public void move() {
		super.move();
    	System.out.println("move-sedan");
    }
	
	@Override
	public void getDetails() {
		super.getDetails();
		System.out.println("weight: "+weight+"\nBootspace area: "+bootspace_area+"\nhas_cng: "+has_cng);
	}
	
}
