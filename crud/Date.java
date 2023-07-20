package crud;

public class Date {
	int dd,mm,yy;
	public Date(int dd, int mm, int yy) {
		this.dd = dd;
		this.mm = mm;
		this.yy = yy;
	}
	public String getDate() {return (this.dd+"."+this.mm+"."+this.yy);}
	public int getYear() {return this.yy;}
}
