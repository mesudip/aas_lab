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
<<<<<<< HEAD
		
		//cube1=new FancyCube();
		//cube1.transform.scale(100);
		//cube1.transform.translate(700, 0);
=======
		cube1=new FancyCube();
		cube2=new FancyCube();
<<<<<<< HEAD
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
=======
		//Camera.getCamera().getTransform().translate(100,100,0);	
		//Camera.getCamera().rotatexOnDrag(-500,0);
		cube1.transform.scale(100);
		//cube1.transform.translate(500,500,30);
		
		cube2.transform.scale(100);
>>>>>>> master
		
		//cube1.transform.perspective();
		cube1.transform.rotatey(10);
		cube1.transform.rotatez(0);
		cube1.transform.rotatex(5);
		
		cube2.transform.rotatey(10);
		cube2.transform.rotatez(0);
		cube2.transform.rotatex(5);
		Camera.getCamera().setOrthoProjection(150,200,150,200,20,50);
		//Camera.getCamera().setOrthoProjection(1000,2000);
		
		cube1.transform.translate(20,40,0);
>>>>>>> 9797e36353c24c3b7fe14bc2905328e9f8601641
		//cube1.transform.perspective();
		//cube2.transform.perspective();
		//
		
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
<<<<<<< HEAD
		cube1.transform.rotatey(0.5);
=======
		//cube1.transform.rotatey(0);
				//cube1.transform.rotatez(-30);
				//cube1.transform.rotatex(-30);
		//cube1.transform.rotatey(0.5);
		//cube1.transform.rotatez(0.5);
		//cube1.transform.rotatex(0.25);
		
		//cube2.transform.rotatey(0.25);
		//cube2.transform.rotatez(0.55);
		//cube2.transform.rotatex(0.25);
>>>>>>> master
		
	}
}
