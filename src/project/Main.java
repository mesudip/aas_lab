package project;
import gcore.Camera;
import gcore.Object3d;
import gprimitive.*;

public class Main {
	static public void main(String[] args){
		Object3d.noLog();
		gcore.Display.initialize();
		
		//Cube cube=new Cube();//a unit cube with center at origin.
		
		//cube.transform.translate(100,100);
		//cube.transform.scale(100,100,100);
		double val=Math.acos(Math.sqrt(1/3));
		Camera.getCamera().transform.rotatex(30);
		Camera.getCamera().transform.rotatey(30);
		
		
		
		//cube.transform.rotate(1,0 , -1, -Math.acos(Math.sqrt(1/3))*180/Math.PI);
//		cube.transform.printRotation();
		//don't know what it means.
		//Camera.getCamera().setOrthoProjection(-300, 300, -300, 300, 0, 1000);
		System.out.println("Cube's Transformation");
	//	cube.transform.print();
		System.out.println("Camera's Transformation");
		Camera.getCamera().getTransform().print();
		System.out.println("Projection Transformation");
		Camera.getCamera().projection.print();
		
		Rectangle rectangle=new Rectangle(-100,-100,100,100);
		System.out.println("Rectangle's Transform");
		rectangle.transform.rotatez(45);
		rectangle.transform.translate(300, 400);
		rectangle.transform.print();
		new Line(100,100,0,0);
		
		
	}
}
