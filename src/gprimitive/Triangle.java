package gprimitive;

import gcore.Object3d;

public class Triangle extends Object3d {
	int x1,y1,x2,y2,x3,y3;
	public Triangle(int x1,int y1,int x2,int y2,int x3,int y3){
		this.x1=x1;this.y1=y1;this.x2=x2;this.y2=y2;this.x3=x3;this.y3=y3;
	}
	public void draw() {
		// TODO Auto-generated method stub
		drawTriangle(addVertex(x1, y1, 0), addVertex(x2, y2, 0), addVertex(x3, y3, 0));
	}
}
