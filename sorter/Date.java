package com.sorter;

public class Date implements Sortable{
	private int dd,mm,yy;
	
	public void setDate(int d, int m, int y){
		dd = d;
		mm = m;
		yy = y;
	}

	@Override
	public void sort(Sortable[] e) {
		e = (Date[]) e;
		Date tmp;
		for(int i=0; i < e.length; i++) {
			for(int j=1; j < (e.length-i); j++) {
				if(e[j-1].yy>e[j].yy) {
					tmp = e[j-1];
					e[j-1] = e[j];
					e[j] = tmp;
				}
			}
		}
	}
	public void print() {
		System.out.println(dd+"-"+mm+"-"+yy);
	}
}