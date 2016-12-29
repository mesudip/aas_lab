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
	 * @deprecated Because it can be implemented in better way.
	 * this function can be implemented without creating a new
	 * matrix. we can copy paste the multiplication code and replace
	 * the indices directly with values and there are lots of zeros 
	 * :)
	 */
	public void translate(Vector3d vector){

		float matrix[][]=new float[4][4];
		matrix[0][0]=matrix[1][1]=matrix[2][2]=matrix[3][3]=1;
		matrix[0][1]=matrix[0][2]=matrix[1][0]=
		matrix[1][2]=matrix[2][0]=matrix[2][1]=
		matrix[3][0]=matrix[3][1]=matrix[3][2]=0;
		matrix[0][3]=vector.x;
		matrix[1][3]=vector.y;
		matrix[2][3]=vector.z;
		this.multiply(matrix);
		
	}
	public void rotate(){/*I don't know how 3d rotation works!*/}
	public void scale(Vector3d vector){
		/* 
		 * @author sudip
		 * Implementation for scaling about origin.
		 * But i think the default scale function should
		 * be the one that scales the object about its own
		 * center.
		 */
		float matrix[][]=new float[4][4];
		matrix[1][1]=vector.x;
		matrix[2][2]=vector.y;
		matrix[3][3]=vector.z;
		matrix[0][1]=matrix[0][2]=matrix[0][3]=matrix[1][0]=
		matrix[1][2]=matrix[1][3]=matrix[2][0]=matrix[2][1]=
		matrix[2][3]=matrix[3][0]=matrix[3][1]=matrix[3][2]=0;
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
	public void multiply(float matrix[][]){
		
		float swap1,swap2,swap3;
		swap1=matrix[0][0];swap2=matrix[0][1];swap3=matrix[0][2];
		matrix[0][0]=swap1*matrix[0][0]+
				swap2*matrix[1][0]+
				swap3*matrix[2][0]+
				matrix[0][3]*matrix[3][0];
		matrix[0][1]=swap1*matrix[0][1]+
				swap2*matrix[1][1]+
				swap3*matrix[2][1]+
				matrix[0][3]*matrix[3][1];
		matrix[0][2]=swap1*matrix[0][2]+
				swap2*matrix[1][2]+
				swap3*matrix[2][2]+
				matrix[0][3]*matrix[3][2];
		matrix[0][3]=swap1*matrix[0][3]+
				swap2*matrix[1][3]+
				swap3*matrix[2][3]+
				matrix[0][3]*matrix[3][3];
		
		swap1=matrix[1][0];swap2=matrix[1][1];swap3=matrix[1][2];
		matrix[1][0]=swap1*matrix[0][0]+
				swap2*matrix[1][0]+
				swap3*matrix[2][0]+
				matrix[1][3]*matrix[3][0];
		matrix[1][1]=swap1*matrix[0][1]+
				swap2*matrix[1][1]+
				swap3*matrix[2][1]+
				matrix[1][3]*matrix[3][1];
		matrix[1][2]=swap1*matrix[0][2]+
				swap2*matrix[1][2]+
				swap3*matrix[2][2]+
				matrix[1][3]*matrix[3][2];
		matrix[1][3]=swap1*matrix[0][3]+
				swap2*matrix[1][3]+
				swap3*matrix[2][3]+
				matrix[1][3]*matrix[3][3];
		
		swap1=matrix[2][0];swap2=matrix[2][1];swap3=matrix[2][2];
		matrix[2][0]=swap1*matrix[0][0]+
				swap2*matrix[1][0]+
				swap3*matrix[2][0]+
				matrix[2][3]*matrix[3][0];
		matrix[2][1]=swap1*matrix[0][1]+
				swap2*matrix[1][1]+
				swap3*matrix[2][1]+
				matrix[2][3]*matrix[3][1];
		matrix[2][2]=swap1*matrix[0][2]+
				swap2*matrix[1][2]+
				swap3*matrix[2][2]+
				matrix[2][3]*matrix[3][2];
		matrix[2][3]=swap1*matrix[0][3]+
				swap2*matrix[1][3]+
				swap3*matrix[2][3]+
				matrix[2][3]*matrix[3][3];
		
		swap1=matrix[3][0];swap2=matrix[3][1];swap3=matrix[3][2];
		matrix[3][0]=swap1*matrix[0][0]+
				swap2*matrix[1][0]+
				swap3*matrix[2][0]+
				matrix[3][3]*matrix[3][0];
		matrix[3][1]=swap1*matrix[0][1]+
				swap2*matrix[1][1]+
				swap3*matrix[2][1]+
				matrix[3][3]*matrix[3][1];
		matrix[3][2]=swap1*matrix[0][2]+
				swap2*matrix[1][2]+
				swap3*matrix[2][2]+
				matrix[3][3]*matrix[3][2];
		matrix[3][3]=swap1*matrix[0][3]+
				swap2*matrix[1][3]+
				swap3*matrix[2][3]+
				matrix[3][3]*matrix[3][3];
		
		
	}
	
}
