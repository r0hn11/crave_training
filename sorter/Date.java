package com.sorter;

public class Date implements Sortable<Date>{
	private int dd,mm,yy;
	
	public Date(int d, int m, int y){
		this.dd = d;
		this.mm = m;
		this.yy = y;
	}

	@Override
	public void sort(Date[] e) {
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