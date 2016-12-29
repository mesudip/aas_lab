package gcore;

public class Color extends Object {
	
	public int rgba;
	
	Color( int rgba){
		this.rgba=rgba;
	}
	Color(int r, int g, int b, int a){
		r=r<<24+g<<16+b<<8+a;
	}
}
