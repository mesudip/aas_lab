package gcore;


public class Lighting {
	
	double intensity;
	
	public static double getIntensity(double[] lightNormal,double[] surfaceNormal,double lightIntensity){
		return (lightNormal[0]*surfaceNormal[0]+
				lightNormal[1]*surfaceNormal[1]+
				lightNormal[2]*surfaceNormal[2])
				*lightIntensity;
	}

}
