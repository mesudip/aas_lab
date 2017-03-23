package gcore;
import java.util.ArrayList;
import java.util.*;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import gmath.Transform;
public class LightSource extends Object {
	static ArrayList<LightSource> lightSources=new ArrayList<LightSource>();
	public int front;
	static ArrayList<LightSource>getAllSources(){
		return lightSources;
	}
	double[] direction=new double[3];
	double intensity;
	Transform transform=new Transform();
	public LightSource(){
		lightSources.add(this);
		
		intensity=1;
		transform.loadIdentity();
		transform.setLocalFront(1, 0, 0);
	}
	
	public LightSource(double x,double y, double z,double intensity){
		lightSources.add(this);
		double r=Math.sqrt(x*x+y*y+z*z);
		direction[0]=x/r;
		direction[1]=y/r;
		direction[2]=z/r;
		
		transform.setLocalFront(direction[0], direction[1], direction[2]);
		this.intensity=intensity;
	}
	
	public double[] getNormal(){
		return direction;
	}
	
	public double getIntensity(){
		return intensity;
	}
}
