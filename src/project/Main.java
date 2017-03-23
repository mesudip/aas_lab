package project;
import java.io.File;

import gmath.Vector;
import gcomposite.DynamicObject3d;
import gcomposite.FancyCube;
import gcore.Camera;
import gcore.LightSource;
import gcore.Object3d;
import gprimitive.*;


public class Main {
	static Cube cube1,cube2,cube3;static Object3d object;
	static{
		new Line(0,0,0,1000,0,0).setColor(0xffff0000);
		new Line(0,0,0,0,1000,0).setColor(0xff00ff00);
		new Line(0,0,0,0,0,1000).setColor(0xff0000ff);
		
		new Line(0,0,0,-1000,0,0).setColor(0xffffff00);
		new Line(0,0,0,0,-1000,0).setColor(0xff00ffff);
		new Line(0,0,0,0,0,-1000).setColor(0xffff00ff);
		
		Object3d.noLog();
		Camera camera=Camera.getCamera();
	}
	

	
	static Camera camera=Camera.getCamera();

	static public void onUpdate(){
	//	object.transform.rotatez(1);
		
	}
	static public void main(String[] args){
//		for(int i=-1000;i<=1000;i+=20){
//			new Line(-1000,i,1000,i).setColor(0xff777700);
//			new Line(i,-1000,i,1000).setColor(0xff777700);
//		}

		LightSource light=new LightSource(1.0,0.0,0.0,1);

		System.out.println("Working Directory = " +System.getProperty("user.dir"));
		//Camera.getCamera().transform.translate(0, 0,500);
		try{
			object=new DynamicObject3d(new File("objFiles/toroid.obj"));
			object.transform.rotatex(90);
		//
		}
		catch(Exception e){
			System.out.println("Exception occured");
			e.printStackTrace(System.out);
		}
		gcore.Display.initialize();
	}
}
