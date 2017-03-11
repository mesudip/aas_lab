package gcore;

public class Lighting {
	
	
	
	public static double getColorIntensity(double[] lightNormal, float[] cameraNormal, double[] surfaceNormal,double lightIntensity){
		
	
		double ambientConstant=1;
		double ambientIntensity=1;
		double diffuseConstant=1;
		
		double colorIntensity = ambientConstant*ambientIntensity+
				diffuseConstant*lightIntensity*(lightNormal[0]*surfaceNormal[0]+
						lightNormal[1]*surfaceNormal[1]+lightNormal[2]*surfaceNormal[2]);
		
		System.out.println(Math.round(colorIntensity/2));
		return colorIntensity/2;
		
	}

}
