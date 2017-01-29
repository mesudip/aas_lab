package project;
import gcore.Camera;
import gcore.Object3d;
import gprimitive.*;

public class Main {
	static public void main(String[] args){
		Object3d.noLog();
		gcore.Display.initialize();
		
		Cube cube=new Cube();//a unit cube with center at origin.
		
		//cube.transform.translate(100,100);
		cube.transform.scale(100,100,100);
		double val=Math.acos(Math.sqrt(1/3));
		cube.transform.rotate(1, 1, 1, 45);
		
		
		
		//cube.transform.rotate(1,0 , -1, -Math.acos(Math.sqrt(1/3))*180/Math.PI);
		cube.transform.printRotation();
		//don't know what it means.
		//Camera.getCamera().setOrthoProjection(-300, 300, -300, 300, 0, 1000);
		System.out.println("Cube's Transformation");
		cube.transform.print();
		System.out.println("Camera's Transformation");
		Camera.getCamera().getTransform().print();
		System.out.println("Projection Transformation");
		Camera.getCamera().porjectionTransform().print();
		/*
		cube.transform.rotate(0, 0, 1, 54.735610317);
		cube.transform.rotate(0, 1, 0, 54.735610317);
		cube.transform.rotate(1, 0, 0, 54.735610317);
		cube.transform.scale(100, 100, 100);
		cube.transform.translate(300, 300);
		cube.transform.printRotation();
		*/
	}
}
