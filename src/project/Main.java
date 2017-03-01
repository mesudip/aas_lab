package project;
import gcore.Camera;
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
		
		System.out.println("Cube's Transformation");
	//	cube.transform.print();
		System.out.println("Camera's Transformation");
		Camera.getCamera().getTransform().print();
		System.out.println("Projection Transformation");
		Camera.getCamera().projection.print();
		
		//cube1=new FancyCube();
		//cube1.transform.scale(100);
		//cube1.transform.translate(700, 0);
		
		
		//cube2.transform.rotatex(45);
		Camera.getCamera().transform.moveTo(500, 500, 100);
		Camera.getCamera().transform.setRotation(-500, -500, -100);
		Camera.getCamera().setPerspective();
		for(int i=-1000;i<=1000;i+=20){
			new Line(-1000,i,1000,i).setColor(0xff777700);
			new Line(i,-1000,i,1000).setColor(0xff777700);
		}
		
	}
	static public void onUpdate(){
		cube1.transform.rotatey(0.5);
		
	}
}
