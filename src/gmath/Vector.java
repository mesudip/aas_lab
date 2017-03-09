package gmath;

import com.sun.beans.editors.DoubleEditor;
import com.sun.corba.se.impl.protocol.AddressingDispositionException;

public class Vector {
	protected double[] vector;
	
	public static double[] crossProduct(double x1,double y1,double z1,double x2,double y2, double z2){
		double[] result=new double[3];
		result[0]=y1*z2-y2*z1;
		result[1]=z1*x2+z2*x1;
		result[2]=x1*y2-x2*y1;
		return result;
	}
	public static double dotProduct(double x1, double y1,double z1,double x2, double y2, double z2){
		return x1*x2+y2*y2+z1*z2;
	}
	public static double angle(double x1,double y1,double z1,double x2,double y2, double z2){
		double r1,r2;
		r1=java.lang.Math.sqrt(x1*x1+y1*y1+z1*z1);
		r2=java.lang.Math.sqrt(x2*x2+y2*y2+z2*z2);
		r1*=r2;
		r2=(x1*x2+y2*y1+z1*z2);
		r2/=r1;
		r1=java.lang.Math.acos(r2);
		double val=r1/java.lang.Math.PI*180;
		if (Double.isNaN(val)){
			return 0;
		}
		return val;
	}
	
	static public void printVector(float[] vector){
		System.out.print("Vector :");
		for(float v :vector){
			System.out.print(""+v+", ");
		}
		System.out.println();
	}
	static public void printVector(double[] vector){
		System.out.print("Vector :");
		for(double v :vector){
			System.out.print(""+v+", ");
		}
		System.out.println();
	}
	
	public static void addTo(double[] v1,double[] v2){
		for(int i=0;i<v1.length && i<v2.length;i++){
			v1[i]+=v2[i];
		}
	}
	public static void substractFrom(double[] v1,double[] v2){
		for(int i=0;i<v1.length && i<v2.length;i++){
			v1[i]-=v2[i];
		}
	}
	public static void addTo(float[] v1,float[] v2){
		for(int i=0;i<v1.length && i<v2.length;i++){
			v1[i]+=v2[i];
		}
	}
	public static void substractFrom(float[] v1,float[] v2){
		for(int i=0;i<v1.length && i<v2.length;i++){
			v1[i]-=v2[i];
		}
	}
	public static double[] vectorCopy(double[] v){
		double[] ret=new double[v.length];
		for(int i=0;i<v.length;i++){
			ret[i]=v[i];
		}
		return ret;
	}
	public static void makeUnit(double[] v){
		double r= java.lang.Math.sqrt(v[0]*v[0]+v[1]*v[1]+v[2]*v[2]);
		v[0]/=r;
		v[1]/=r;
		v[2]/=r;
	}
	public static double[] getUnit (double[] v){
		double[] unit=new double[3];
		double r= java.lang.Math.sqrt(v[0]*v[0]+v[1]*v[1]+v[2]*v[2]);
		unit[0]=v[0]/r;
		unit[1]=v[1]/r;
		unit[2]=v[2]/r;
		return unit;
	}
	
	
}
class Vector3 extends Vector{
	Vector3(){
		vector=new double[3];
	}
	public Vector3(double u,double v, double w){
		vector=new double[3];
		vector[0]=u;
		vector[1]=v;
		vector[2]=w;
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
}
class Vector4 extends Vector{
	public Vector4() {
		vector=new double[4];
	}
	public Vector4(double u,double v, double w,double y){
		vector=new double[4];
		vector[0]=u;
		vector[1]=v;
		vector[2]=w;
		vector[3]=y;
	}
	
}
class Vector2 extends Vector{
	
}
