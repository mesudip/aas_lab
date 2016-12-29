package gcore;
	
import gcore.Vector3d;
import rendercore.RenderStage;

import java.util.Set;

public class Object3d extends WorldObject implements Drawable{
	private rendercore.RenderStage renderStage=RenderStage.Wireframe;
	private static final Set<Drawable> objectList=new java.util.HashSet<>();
	final public static Set<Drawable> getObjectlist() {
		return  objectList;
	}
	Vector3d vertices[];
	int faces[][];
	int edges[][];
		
	/**
	 * @author sudip
	 * Let's not use other functions for creating a Object3d
	 */
	public Object3d(){
		objectList.add(this);
		
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
	public void draw(){
		/* TODO add some functionality here*/
	}
	
}
