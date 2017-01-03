package gcore;
	
import gcore.Vector3d;
import rendercore.RenderStage;

import java.util.List;


import java.util.Set;

public class Object3d extends WorldObject implements Drawable{
	Vector3d vertices[];
	int faces[][];
	int edges[][];
		
	private rendercore.RenderStage renderStage=RenderStage.Wireframe;
	private static final Set<Object3d> objectList=new java.util.HashSet<>();
	final public static Set<Object3d> getObjectlist() {
		return  objectList;
	}
	/**
	 * @author sudip
	 * Let's not use other functions for creating a Object3d
	 */
	public Object3d(){
		objectList.add(this);
		
	}
	protected rendercore.RenderRegistry getRegister(){
		return rendercore.RenderRegistry.getDefaultRegistor();
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
	protected final int addVertex(Vector4d[] vector){
		return currentWorld.getRegistry().registerVector(vector);
	}
	protected final void setVertexHint(int count){
		currentWorld.getRegistry().addVertexCountHint(count);
	}
	protected final void setLineHint(int count){
		currentWorld.getRegistry().addLineCountHint(count);
	}
	protected final List<Vector4d> getVertexList(int offset, int count){
		return currentWorld.getRegistry().getVectorList(offset, count);
	}
	protected final List<Vector4d> getNewVertices(int count){
		return new java.util.ArrayList<Vector4d>();

	}
	protected final void drawEdge(){

	}
	protected final void drawFace(){}
	protected final void drawLine(int[] vector){
		currentWorld.getRegistry().drawLine(vector);
	}
	protected final void drawLine(int offset, int[] vector){
		currentWorld.getRegistry().drawLine(offset, vector);
	}
	protected final void drawLine(int start, int end){
		currentWorld.getRegistry().drawLine(start, end);
	}
	protected final void drawPoint(){}

	
}
