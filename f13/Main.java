package f13;

public class Main {
	public static void main(String[] args) {
//		int reg_no, String fuel_type, double speed, double engine_cap, double avg_price, int seat_count, WEIGHT weight, double bootspace_area, boolean has_cng
		Vehicle c = new Sedan(235,"Petrol",150.5,2000,35.5,4,WEIGHT.HEAVY,4, true);
		c.move();
		c.getDetails();
	}
}
