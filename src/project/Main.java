package project;
import java.io.File;

import gcomposite.DynamicObject3d;
import gcomposite.FancyCube;
import gcore.Camera;
import gcore.DirectionalLight;
import gcore.Object3d;
import gprimitive.*;


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
		cube1=new FancyCube();
		
		
		DirectionalLight light=new DirectionalLight(1,0,-1,1);
		
		cube1.transform.scale(100);
		Camera.getCamera().setOrthoProjection(10, 200,100,200,50,100);;
		Camera.getCamera().transform.moveTo(600, 600, 600);
		Camera.getCamera().transform.setRotation(-600, -600, -600);
		
		for(int i=-1000;i<=1000;i+=20){
			new Line(-1000,i,1000,i).setColor(0xff777700);
			new Line(i,-1000,i,1000).setColor(0xff777700);
		}
		
	}
	static public void onUpdate(){
		cube1.transform.rotatey(0.5);
	}
	static public void _main(String[] args){
		try{
		new DynamicObject3d(new File("/home/paradox/Documents/Workspace/pk_Graphics/objects/cube.obj"));
		}
		catch(Exception e){
			System.out.println("Exception occured");
			e.printStackTrace(System.out);
		}
	}
}
