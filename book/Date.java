public class Date{
	private int dd,mm,yy;
	
	public void setDate(int d, int m, int y){
		dd = d;
		mm = m;
		yy = y;
	}	
	public String getDate(){return dd+"-"+mm+"-"+yy;}
}
