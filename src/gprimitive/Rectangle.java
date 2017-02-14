package gprimitive;

import gcore.Object3d;

public class Rectangle extends Object3d {
	int x1,y1,x2,y2;
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
		
		setColor(0x00ffff00);
		drawTriangle(c1, c2,addVertex(x1, y2, 0));
		setColor(0x0000ffff);
		drawTriangle(c1, c2,addVertex(x2, y1,0));
		
	}

}
