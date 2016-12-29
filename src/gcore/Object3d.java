package gcore;
import gcore.Vector3d;

public class Object3d extends WorldObject {
	Vector3d vertices[];
	int faces[][];
	int edges[][];
	
	public Object3d(){
		
	}
	
	public Object3d(Vector3d vertexArray[], int faceArray[][]){
		vertices=vertexArray;
		faces=faceArray;
	}
	
	public Object3d(Vector3d vertexArray[],int faceArray[][],int edgeArray[][]){
		vertices=vertexArray;
		faces=faceArray;
		edges=edgeArray;
	}
	
}
