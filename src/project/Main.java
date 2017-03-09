package project;
import gcomposite.FancyCube;
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
		cube1=new FancyCube();
		
		cube1.transform.scale(100);
		
		float[] forward=Camera.getCamera().transform.getRotatedVector(0, 0,-1);
		//float[] forwar=Camera.getCamera().transform.getPosition();
		//Camera.getCamera().transform.moveTo(10, 10,10);
	   Camera.getCamera().transform.setRotation(30,30,30);
		cube1.transform.rotatey(50);
		cube1.transform.rotatez(50);
		cube1.transform.rotatex(50);
		Camera.getCamera().transform.moveTo(40,40,-30);
		//Camera.getCamera().setOrthoProjection(10, 100,10,100,60,forward[1]);
		//Camera.getCamera().getProjection().printPosition();
		//Camera.getCamera().p();
		for(int i=-1000;i<=1000;i+=20){
			new Line(-1000,i,1000,i).setColor(0xff777700);
			new Line(i,-1000,i,1000).setColor(0xff777700);
		}
		
	}
	static public void onUpdate(){
		cube1.transform.rotatey(0.5);
		cube1.transform.rotatex(0.5);
		cube1.transform.rotatez(0.5);
	}
}
