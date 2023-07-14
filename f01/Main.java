public class Main{
	public static void main(String[] args){
		Date pd = new Date();
		pd.setDate(3,7,2023);
		String publish_date = pd.getDate();
		float price = (float)120.5;
		Book b1 = new Book();
		b1.setBookData(1,"The Book","The Author", "The Genre", (float)price, publish_date);
		b1.showBookData();
	}
}