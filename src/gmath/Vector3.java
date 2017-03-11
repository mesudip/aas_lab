package gmath;

import gcore.Object;

public class Vector3 extends Object {
	public double x,y,z;
	public Vector3(){
	}
	public Vector3(Vector3 vector){
		x=vector.x;y=vector.y;z=vector.z;
	}
	public Vector3(double u,double v, double w){
		
		x=u;
		y=v;
		z=w;
	}
	public static float[] crossProduct(float[] v1, float[] v2){
		float[] result=new float[3];
		result[0]=v1[1]*v2[2]-v2[1]*v1[2];
		result[1]=v1[2]*v2[0]-v2[2]*v1[0];
		result[2]=v1[0]*v2[1]-v2[0]*v1[1];
		return result;
	}
	public static double[] crossProduct(double[] v1, double[] v2){
		double[] result=new double[3];
		result[0]=v1[1]*v2[2]-v2[1]*v1[2];
		result[1]=v1[2]*v2[0]-v2[2]*v1[0];
		result[2]=v1[0]*v2[1]-v2[0]*v1[1];
		return result;
	}
	public static double angle(float[] v1 ,float[] v2){
		return java.lang.Math.acos((v1[0]*v2[0]+v1[1]*v2[1]+v1[2]*v2[2])/java.lang.Math.sqrt(v1[0]*v1[0]+v1[1]*v1[1]+v1[2]*v1[2])/java.lang.Math.sqrt(v2[0]*v2[0]+v2[1]*v2[1]+v2[2]*v2[2]))/java.lang.Math.PI*180;
	}
	public static double dotProduct(float[] v1,float[]v2){
		return v1[0]*v2[0]+v1[1]*v2[1]+v1[2]*v2[2];
	}
	public void makeUnit(){
		double r=java.lang.Math.sqrt(x*x+y*y+z*z);
		x/=r;y/=r;z/=r;
	}
	public Vector3 getUnit(){
		double r=java.lang.Math.sqrt(x*x+y*y+z*z);
		return  new Vector3(x/r,y/r,z/r);
	}
	public Vector3 getCopy(){
		return new Vector3(x,y,z);
	}
}
