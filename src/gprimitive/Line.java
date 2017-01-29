package gprimitive;

import gcore.Object3d;

public class Line extends gcore.Object3d{
	private float x1,y1,z1;
	private float x2,y2,z2;
	static int static_color;
	private int color=Object3d.activeColor;
	public Line(int x1,int y1,int x2,int y2){
		this.x1=x1;this.y1=y1;this.x2=x2;this.y2=y2;
		setPosition((x1+x2)/2, (y1+y2)/2, 0);
	}
	public Line (float x1,float y1, float x2, float y2){
		this.x1=x1;this.y1=y1;
		this.x2=x2;this.y2=y2;
		setPosition((x1+x2)/2, (y1+y2)/2, 0);
	}
	public Line (float x1,float y1, float z1, float x2, float y2, float z2){
		this.x1=x1;this.y1=y1;this.z1=z1;
		this.x2=x2;this.y2=y2;this.z2=z2;
		setPosition((x1+x2)/2, (y1+y2)/2, (z1+z2)/2);
	}
	public void draw(){
		drawLine(x1,y1,z1,x2,y2,z2);
	}
	
	public void setColor(int color){
		this.color=color;
	}
}
