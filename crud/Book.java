package crud;

public class Book {
	String title, author,category;
	Date publish_date;

	public Book(String title, String author, String category, Date publish_date) {
		this.title = title;
		this.author = author;
		this.category = category;
		this.publish_date = publish_date;
	}
	public String getTitle() {return this.title;}
	public String getAuthor() {return this.author;}
	public String getCategory() {return this.category;}
	public int getPublishyr() {return this.publish_date.getYear();}
	
	public void getBookData() {
		System.out.println("Title: "+this.title+"\nAuthor: "+this.author+"\nCategory: "+this.category+"\nPublish year: "+this.publish_date.getYear());
	}
	
}
