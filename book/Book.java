public class Book{
	private int id;
	private String title, author, genre;
	private float price;
	private String publish_date;
	
	public void setBookData(int _id, String _title, String _author, String _genre, float _price, String _publish_date){
		this.id=_id;
		title=_title;
		author=_author;
		genre=_genre;
		price=_price;
		publish_date=_publish_date;
	}	
	public void showBookData(){
		System.out.println("id: "+id+"\nTitle: "+title+"\nAuthor: "+author+"\nGenre: "+genre+"\nPrice: Rs."+price);
	}
}
