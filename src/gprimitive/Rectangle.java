package gprimitive;


import gcore.Object3d;

public class Rectangle extends Object3d {
	int x1,y1,x2,y2;
	int color=0xff000000;
	private boolean wire=false;
	public Rectangle(){
		x1=-1;x2=1;y1=-1;y2=1;
	}
	public Rectangle(int x1,int y1,int x2,int y2){
		if(x2<x1){
			this.x2=x1;
			this.x1=x2;
		}
		else{
			this.x1=x1;
			this.x2=x2;
		}
		if(y1<y2){
			this.y1=y1;
			this.y2=y2;
		}
		else{
			this.y2=y1;
			this.y1=y2;
		}
		setPosition((x1+x2)/2, (y1+y2)/2, 0);
		
	}
	public void draw() {
		int c1=addVertex(x1, y1, 0);
		int c2=addVertex(x2, y2, 0);
		super.setColor(color);
		if(wire){
			int c3=addVertex(x1, y2, 0);
			drawLine(c1, c3);
			drawLine(c2, c3);
			c3=addVertex(x2, y1,0);
			drawLine(c1, c3);
			drawLine(c2, c3);
		}
		else{
			drawTriangle(c1, c2,addVertex(x1, y2, 0));
			drawTriangle(c2, c1,addVertex(x2, y1,0));
		}
		
	}
	public void setColor(int color){
		this.color=color;
	}
	public void makeWireFrame(){
		wire=true;
	}
	public void makeSolid(){
		wire=false;
	}

}
