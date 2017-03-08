package gprimitive;

import gcore.Object3d;

public class Triangle extends Object3d {
	int x1,y1,z1,x2,y2,z2,x3,y3,z3;
	int color;
	public Triangle(){
		x1=-1;y1=-1;
		x2=1;y2=-1;
		x3=0;y3=1;
		z1=z2=0;
	}
	public Triangle(int x1,int y1,int x2,int y2,int x3,int y3){
		this.x1=x1;this.y1=y1;this.x2=x2;this.y2=y2;this.x3=x3;this.y3=y3;
		z1=z2=0;
		setPosition((x1+x2+x3)/3, (y1+y2+y3)/3,0);
	}
	public Triangle(int x1,int y1,int z1,int x2,int y2,int z2,int x3,int y3,int z3){
		this.x1=x1;this.y1=y1;this.z1=z1;this.x2=x2;this.y2=y2;this.z2=z2;this.x3=x3;this.y3=y3;this.z3=z3;
		setPosition((x1+x2+x3)/3, (y1+y2+y3)/3,(z1+z2+z3)/3);
	}
	public void draw() {
		// TODO Auto-generated method stub
		activeColor=color;
		drawTriangle(addVertex(x1, y1, z1), addVertex(x2, y2, z2), addVertex(x3, y3, z3));
	}
	public void setColor(int color){
		this.color=color;
	}
}
