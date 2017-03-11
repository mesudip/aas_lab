package gcore;

import java.util.ArrayList;
import java.util.List;

public class DirectionalLight extends gcore.Object{
	
	double[] direction=new double[3];
	double intensity;
	static List<DirectionalLight> lights=new ArrayList<DirectionalLight>();
	public static List<DirectionalLight> getLights() {
		return lights;
	}

	public DirectionalLight(){
		
		direction[0]=0;
		direction[1]=0;
		direction[2]=0;
		intensity=1;
		
		lights.add(this);
	}
	
	public DirectionalLight(double x, double y, double z,double intensity){
		double r=Math.sqrt(x*x+y*y+z*z);
		direction[0]=x/r;
		direction[1]=y/r;
		direction[2]=z/r;
		this.intensity=intensity;
		
		lights.add(this);
	}
	
	public double[] getUnitNormalVector(){
		return direction;
	}
	
	public double getIntensity(){
		return intensity;
	}
	
	
}
