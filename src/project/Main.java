package project;
import gcomposite.FancyCube;
import gcore.Camera;
import gcore.Object3d;
import gprimitive.*;
import jdk.internal.dynalink.beans.StaticClass;
import sun.launcher.resources.launcher;

public class Main {
	static Cube cube1,cube2,cube3;
	static{
		new Line(-1000,0,0,1000,0,0).setColor(0xffff0000);
		new Line(0,-1000,0,0,1000,0).setColor(0xff00ff00);
		new Line(0,0,-1000,0,0,1000).setColor(0xff0000ff);
	}
	
	static public void main(String[] args){
		Object3d.noLog();
		gcore.Display.initialize();
		
		System.out.println("Cube's Transformation");
	//	cube.transform.print();
		System.out.println("Camera's Transformation");
		Camera.getCamera().getTransform().print();
		System.out.println("Projection Transformation");
		Camera.getCamera().projection.print();
		cube1=new FancyCube();
		cube1.transform.scale(100);
	
		
		
		
	}
	static public void onUpdate(){
		cube1.transform.rotatey(0.5);
		cube1.transform.rotatez(0.5);
		cube1.transform.rotatex(0.25);
	}
}
