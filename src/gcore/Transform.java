package gcore;	

/**
 * @author sudip
 * Date : Dec 29, 2016
 * Well this class contains the transformation matrix and has functions for
 * some default operations.
 * Each transformable object will have a Transform as a member.
 * That transform will be applied as a Molelling Transformation.
 */
public class Transform extends Object {
	public enum Type{Transform,Rotation,Scale,Shear};
	static final Transform Identity=new Transform();
	private float matrix[][];
	Transform(){
		matrix=new float[4][4];
	}
	public void loadIdentity(){
		matrix[0][0]=matrix[1][1]=matrix[2][2]=matrix[3][3]=1;
		matrix[0][1]=matrix[0][2]=matrix[0][3]=matrix[1][0]=
		matrix[1][2]=matrix[1][3]=matrix[2][0]=matrix[2][1]=
		matrix[2][3]=matrix[3][0]=matrix[3][1]=matrix[3][2]=0;
	}
	/**
	 * @author sudip
	 * It uses the multiplication algorithm, but since there are lot of zeros, 
	 * this is gonna be simpler and faster than making a new matrix and 
	 * applying general multiplication algorithm,
	 */
	public void translate(Vector3d vector){
		matrix[0][3]=matrix[0][0]*vector.x+matrix[0][1]*vector.y+matrix[0][2]*vector.z+matrix[0][3];
		matrix[1][3]=matrix[1][0]*vector.x+matrix[1][1]*vector.y+matrix[1][2]*vector.z+matrix[0][3];
		matrix[2][3]=matrix[2][0]*vector.x+matrix[2][1]*vector.y+matrix[2][2]*vector.z+matrix[0][3];		
	}
	
	
	/**
	 * @author sudip
	 * @param initpoint start point for line
	 * @param endpoint  end point for line.
	 * -->
	 * Well, start and end points are actually nto the determining factors.
	 * what matters is what are the direction cosines of the line and the
	 * line's distance from the object.
	 * 
	 */
	public void rotate(Vector4d initpoint,Vector4d endpoint){
		float a,b,c,r;
		a=endpoint.x-initpoint.x;
		b=endpoint.y-initpoint.y;
		c=endpoint.z-initpoint.z;
		r=a*a+b*b+c*c;
		
		Transform transform=new Transform();
		transform.loadIdentity();
		
		/*I don't know how 3d general rotation would be!*/
	}
	/**
	 * @author sudip
	 * -->
	 * Rotate Object about x axis.
	 * @param angle Angle will be in degrees.
	 * Should it be in radian for eliminating the conversion?
	 * Well we can change it later if reqired.
	 */
	public void rotateX(double angle){
		angle*=java.lang.Math.PI;
		angle/=180;
		float cos_t=(float)java.lang.Math.cos(angle);
		float sin_t=(float)java.lang.Math.sin(angle);
		float swap;
		
		swap=matrix[0][1]*sin_t;
		matrix[0][1]*=cos_t;
		matrix[0][1]-=matrix[0][2]*sin_t;
		matrix[0][2]*=cos_t;
		matrix[0][2]+=swap;
		
		swap=matrix[1][1]*sin_t;
		matrix[1][1]*=cos_t;
		matrix[1][1]-=matrix[0][2]*sin_t;
		matrix[1][2]*=cos_t;
		matrix[1][2]+=swap;
		
		swap=matrix[2][1]*sin_t;
		matrix[2][1]*=cos_t;
		matrix[2][1]-=matrix[0][2]*sin_t;
		matrix[2][2]*=cos_t;
		matrix[2][2]+=swap;
		
		swap=matrix[3][1]*sin_t;
		matrix[3][1]*=cos_t;
		matrix[3][1]-=matrix[0][2]*sin_t;
		matrix[3][2]*=cos_t;
		matrix[3][2]+=swap;
	}
	/**
	 * @author sudip
	 * -->
	 * Rotate Object about y axis.
	 */
	public void rotateY(double angle){
		angle*=java.lang.Math.PI;
		angle/=180;
		float cos_t=(float)java.lang.Math.cos(angle);
		float sin_t=(float)java.lang.Math.sin(angle);
		float swap;
		
		swap=matrix[0][0]*sin_t;
		matrix[0][0]*=cos_t;
		matrix[0][1]+=matrix[0][2]*sin_t;
		matrix[0][2]*=cos_t;
		matrix[0][2]-=swap;
		
		swap=matrix[1][0]*sin_t;
		matrix[1][0]*=cos_t;
		matrix[1][1]+=matrix[1][2]*sin_t;
		matrix[1][2]*=cos_t;
		matrix[1][2]-=swap;
		
		swap=matrix[2][0]*sin_t;
		matrix[2][0]*=cos_t;
		matrix[2][1]+=matrix[2][2]*sin_t;
		matrix[2][2]*=cos_t;
		matrix[2][2]-=swap;
		
		swap=matrix[0][0]*sin_t;
		matrix[2][0]*=cos_t;
		matrix[2][1]+=matrix[2][2]*sin_t;
		matrix[2][2]*=cos_t;
		matrix[2][2]-=swap;
		
				
	}
	/**
	 * @author sudip
	 * -->
	 * Rotate Object about z axis.
	 */
	public void rotateZ(double angle){
		angle*=java.lang.Math.PI;	
		angle/=180;
		float cos_t=(float)java.lang.Math.cos(angle);
		float sin_t=(float)java.lang.Math.sin(angle);
		float swap;
		
		swap=matrix[0][0]*sin_t;
		matrix[0][0]*=cos_t;
		matrix[0][1]-=matrix[0][11]*sin_t;
		matrix[0][1]*=cos_t;
		matrix[0][1]+=swap;
		
		swap=matrix[1][0]*sin_t;
		matrix[1][0]*=cos_t;
		matrix[1][1]-=matrix[1][1]*sin_t;
		matrix[1][1]*=cos_t;
		matrix[1][1]+=swap;
		
		swap=matrix[2][0]*sin_t;
		matrix[2][0]*=cos_t;
		matrix[2][1]-=matrix[2][1]*sin_t;
		matrix[2][1]*=cos_t;
		matrix[2][1]+=swap;
		
		swap=matrix[3][0]*sin_t;
		matrix[3][0]*=cos_t;
		matrix[3][1]-=matrix[3][1]*sin_t;
		matrix[3][1]*=cos_t;
		matrix[3][1]+=swap;
		
	}
	
	/**
	 * @author sudip
	 * -->
	 * Reflection functions about planes 
	 */
	public void reflectYZ(){
		matrix[0][0]=-matrix[0][0];
		matrix[1][0]=-matrix[1][0];
		matrix[2][0]=-matrix[2][0];
		matrix[3][0]=-matrix[3][0];
	}
	public void reflectXZ(){
		matrix[0][1]=-matrix[0][1];
		matrix[1][1]=-matrix[1][1];
		matrix[2][1]=-matrix[2][1];
		matrix[3][1]=-matrix[3][1];
	}
	public void reflectXY(){
		matrix[0][2]=-matrix[0][2];
		matrix[1][2]=-matrix[1][2];
		matrix[2][2]=-matrix[2][2];
		matrix[3][2]=-matrix[3][2];
	}

	public void scale(Vector3d vector){
		/* 
		 * @author sudip
		 * Implementation for scaling about origin.
		 * But i think the default scale function should
		 * be the one that scales the object about its own
		 * center.
		 * sad : the object's center is not part of transform class.
		 */
		matrix[0][0]*=vector.x;
		matrix[0][1]*=vector.y;
		matrix[0][2]*=vector.z;
		
		matrix[1][0]*=vector.x;
		matrix[1][1]*=vector.y;
		matrix[1][2]*=vector.z;
		
		matrix[2][0]*=vector.x;
		matrix[2][1]*=vector.y;
		matrix[2][2]*=vector.z;
	}
	
	/**
	 * @author sudip
	 * @param transform is the transformation matrix which is to be multiplied to original one.
	 * Trying to make it faster with lesser memory consumption
	 * Because this function is going to be called hell lot of times
	 * And I want to eliminate the necessity of branch prediction
	 * by removing the loop
	 */
	public void multiply(Transform transform){
		
		float swap1,swap2,swap3;
		swap1=matrix[0][0];swap2=matrix[0][1];swap3=matrix[0][2];
		matrix[0][0]=swap1*transform.matrix[0][0]+
				swap2*transform.matrix[1][0]+
				swap3*transform.matrix[2][0]+
				matrix[0][3]*transform.matrix[3][0];
		matrix[0][1]=swap1*transform.matrix[0][1]+
				swap2*transform.matrix[1][1]+
				swap3*transform.matrix[2][1]+
				matrix[0][3]*transform.matrix[3][1];
		matrix[0][2]=swap1*transform.matrix[0][2]+
				swap2*transform.matrix[1][2]+
				swap3*transform.matrix[2][2]+
				matrix[0][3]*transform.matrix[3][2];
		matrix[0][3]=swap1*transform.matrix[0][3]+
				swap2*transform.matrix[1][3]+
				swap3*transform.matrix[2][3]+
				matrix[0][3]*transform.matrix[3][3];
		
		swap1=matrix[1][0];swap2=matrix[1][1];swap3=matrix[1][2];
		matrix[1][0]=swap1*transform.matrix[0][0]+
				swap2*transform.matrix[1][0]+
				swap3*transform.matrix[2][0]+
				matrix[1][3]*transform.matrix[3][0];
		matrix[1][1]=swap1*transform.matrix[0][1]+
				swap2*transform.matrix[1][1]+
				swap3*transform.matrix[2][1]+
				matrix[1][3]*transform.matrix[3][1];
		matrix[1][2]=swap1*transform.matrix[0][2]+
				swap2*transform.matrix[1][2]+
				swap3*transform.matrix[2][2]+
				matrix[1][3]*transform.matrix[3][2];
		matrix[1][3]=swap1*transform.matrix[0][3]+
				swap2*transform.matrix[1][3]+
				swap3*transform.matrix[2][3]+
				matrix[1][3]*transform.matrix[3][3];
		
		swap1=matrix[2][0];swap2=matrix[2][1];swap3=matrix[2][2];
		matrix[2][0]=swap1*transform.matrix[0][0]+
				swap2*transform.matrix[1][0]+
				swap3*transform.matrix[2][0]+
				matrix[2][3]*transform.matrix[3][0];
		matrix[2][1]=swap1*transform.matrix[0][1]+
				swap2*transform.matrix[1][1]+
				swap3*transform.matrix[2][1]+
				matrix[2][3]*transform.matrix[3][1];
		matrix[2][2]=swap1*transform.matrix[0][2]+
				swap2*transform.matrix[1][2]+
				swap3*transform.matrix[2][2]+
				matrix[2][3]*transform.matrix[3][2];
		matrix[2][3]=swap1*transform.matrix[0][3]+
				swap2*transform.matrix[1][3]+
				swap3*transform.matrix[2][3]+
				matrix[2][3]*transform.matrix[3][3];
		
		swap1=matrix[3][0];swap2=matrix[3][1];swap3=matrix[3][2];
		matrix[3][0]=swap1*transform.matrix[0][0]+
				swap2*transform.matrix[1][0]+
				swap3*transform.matrix[2][0]+
				matrix[3][3]*transform.matrix[3][0];
		matrix[3][1]=swap1*transform.matrix[0][1]+
				swap2*transform.matrix[1][1]+
				swap3*transform.matrix[2][1]+
				matrix[3][3]*transform.matrix[3][1];
		matrix[3][2]=swap1*transform.matrix[0][2]+
				swap2*transform.matrix[1][2]+
				swap3*transform.matrix[2][2]+
				matrix[3][3]*transform.matrix[3][2];
		matrix[3][3]=swap1*transform.matrix[0][3]+
				swap2*transform.matrix[1][3]+
				swap3*transform.matrix[2][3]+
				matrix[3][3]*transform.matrix[3][3];		
	}
	
	/** 
	 * @author sudip
	 * @param it has array as input argument. 
	 * I don't know this function is necessary or not
	 * will accessing the array directly make things 
	 * faster than accessing array inside a object?
	 * Well, I don't know but lets hope this runs
	 * faster than the previous one.Otherwiise 
	 * there is no need of this function.
	 */
	public void multiply(float matrix2[][]){
		
		float swap1,swap2,swap3;
		swap1=matrix[0][0];swap2=matrix[0][1];swap3=matrix[0][2];
		matrix[0][0]=swap1*matrix2[0][0]+
				swap2*matrix2[1][0]+
				swap3*matrix2[2][0]+
				matrix[0][3]*matrix2[3][0];
		matrix[0][1]=swap1*matrix2[0][1]+
				swap2*matrix2[1][1]+
				swap3*matrix2[2][1]+
				matrix[0][3]*matrix[3][1];
		matrix[0][2]=swap1*matrix[0][2]+
				swap2*matrix2[1][2]+
				swap3*matrix2[2][2]+
				matrix[0][3]*matrix2[3][2];
		matrix[0][3]=swap1*matrix2[0][3]+
				swap2*matrix2[1][3]+
				swap3*matrix2[2][3]+
				matrix[0][3]*matrix2[3][3];
		
		swap1=matrix[1][0];swap2=matrix[1][1];swap3=matrix[1][2];
		matrix[1][0]=swap1*matrix2[0][0]+
				swap2*matrix2[1][0]+
				swap3*matrix2[2][0]+
				matrix[1][3]*matrix2[3][0];
		matrix[1][1]=swap1*matrix2[0][1]+
				swap2*matrix2[1][1]+
				swap3*matrix2[2][1]+
				matrix[1][3]*matrix2[3][1];
		matrix[1][2]=swap1*matrix2[0][2]+
				swap2*matrix2[1][2]+
				swap3*matrix2[2][2]+
				matrix[1][3]*matrix2[3][2];
		matrix[1][3]=swap1*matrix2[0][3]+
				swap2*matrix2[1][3]+
				swap3*matrix2[2][3]+
				matrix[1][3]*matrix2[3][3];
		
		swap1=matrix[2][0];swap2=matrix[2][1];swap3=matrix[2][2];
		matrix[2][0]=swap1*matrix2[0][0]+
				swap2*matrix2[1][0]+
				swap3*matrix2[2][0]+
				matrix[2][3]*matrix2[3][0];
		matrix[2][1]=swap1*matrix2[0][1]+
				swap2*matrix2[1][1]+
				swap3*matrix2[2][1]+
				matrix[2][3]*matrix2[3][1];
		matrix[2][2]=swap1*matrix2[0][2]+
				swap2*matrix2[1][2]+
				swap3*matrix2[2][2]+
				matrix[2][3]*matrix2[3][2];
		matrix[2][3]=swap1*matrix2[0][3]+
				swap2*matrix2[1][3]+
				swap3*matrix2[2][3]+
				matrix[2][3]*matrix2[3][3];
		
		swap1=matrix[3][0];swap2=matrix[3][1];swap3=matrix[3][2];
		matrix[3][0]=swap1*matrix2[0][0]+
				swap2*matrix2[1][0]+
				swap3*matrix2[2][0]+
				matrix[3][3]*matrix2[3][0];
		matrix[3][1]=swap1*matrix2[0][1]+
				swap2*matrix2[1][1]+
				swap3*matrix2[2][1]+
				matrix[3][3]*matrix2[3][1];
		matrix[3][2]=swap1*matrix2[0][2]+
				swap2*matrix2[1][2]+
				swap3*matrix2[2][2]+
				matrix[3][3]*matrix2[3][2];
		matrix[3][3]=swap1*matrix2[0][3]+
				swap2*matrix2[1][3]+
				swap3*matrix2[2][3]+
				matrix[3][3]*matrix2[3][3];	
	}
	/**
	 * @author sudip
	 * @param list list(java.util.List) of vertices.
	 *  @see rendercore.RenderReistry.useTransform()
	 */
	public void applyOn(java.util.List<Vector4d> list){
		for (Vector4d vector : list) {
			vector.x=matrix[0][0]*vector.x+matrix[0][1]*vector.y+matrix[0][2]*vector.z+matrix[0][3]*vector.w;
			vector.x=matrix[1][0]*vector.x+matrix[1][1]*vector.y+matrix[1][2]*vector.z+matrix[1][3]*vector.w;
			vector.x=matrix[2][0]*vector.x+matrix[2][1]*vector.y+matrix[2][2]*vector.z+matrix[2][3]*vector.w;
			vector.x=matrix[3][0]*vector.x+matrix[3][1]*vector.y+matrix[3][2]*vector.z+matrix[3][3]*vector.w;		
		}
		
		
	}
	
}
