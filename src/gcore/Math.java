package gcore;

public class Math extends Object {
	static public float[] getFrontVector(Object3d object){
		return object.transform.getRotatedVector(1,0, 0);
	}
	static public float[] getFrontVector(Camera camera){
		return camera.transform.getRotatedVector(0, 0, -1);
	}
	static public float[] getUpVector(Object3d object){
		return object.transform.getRotatedVector(0, 0, 1);
	}
	static public float[] getUpVector(Camera camera){
		return camera.transform.getRotatedVector(0, 1, 0);
	}
	
}
