package gcore;
import gcore.Vector3d;

public class Object3d extends WorldObject {
	Vector3d vertices[];
	int faces[][];
	int edges[][];
	
	public Object3d(){
		
	}
	
	public Object3d(Vector3d vertex_array[], int face_array[][]){
		vertices=vertex_array;
		faces=face_array;
	}
	
	public Object3d(Vector3d vertex_array[],int face_array[][],int edge_array[][]){
		vertices=vertex_array;
		faces=face_array;
		edges=edge_array;
	}
}
