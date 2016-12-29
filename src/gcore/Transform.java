package gcore;
public class Transform extends Object {
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
	public void translate(Vector3d vector){}
	public void rotate(){}
	public void scale(Vector3d vector){}
}
