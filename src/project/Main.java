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
		cube2=new FancyCube();
		//Camera.getCamera().getTransform().translate(0,0,-50);	
		Camera.getCamera().getTransform().translate(1000,200,50);
		//Camera.getCamera().rotatexOnDrag(-500,0);
		cube1.transform.scale(200);
		//cube1.transform.translate(50,100,0);
		
		cube2.transform.scale(200);
		
		cube1.transform.perspective();
		cube1.transform.rotatey(-50);
		cube1.transform.rotatez(-50);
		cube1.transform.rotatex(200);
		
		cube2.transform.rotatey(-50);
		cube2.transform.rotatez(-50);
		cube2.transform.rotatex(200);
		Camera.getCamera().setOrthoProjection(10,200,100,200,50,100);
		//Camera.getCamera().setOrthoProjection(1000,2000);
		cube1.transform.translate(-100,200,50);
		cube2.transform.translate(-400,800,350);
		//cube1.transform.perspective();
		//cube2.transform.perspective();
		//
		
	}
	static public void onUpdate(){
		//cube1.transform.rotatey(0);
				//cube1.transform.rotatez(-30);
				//cube1.transform.rotatex(-30);
		//cube1.transform.rotatey(0.5);
		//cube1.transform.rotatez(0.5);
		//cube1.transform.rotatex(0.25);
		
		//cube2.transform.rotatey(0.25);
		//cube2.transform.rotatez(0.55);
		//cube2.transform.rotatex(0.25);
		
	}
}
