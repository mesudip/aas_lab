package rendercore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import gcore.Object3d;
import gprimitive.*;

/**
 * @author sudip
 * Date : Dec 29, 2016
 * -->
 * This class will be the registry point.
 * All objects will register themselves for rendering in their constructor.
 * Then in the draw() function of Drawable Objects, they will register
 * all the primitive types like the vertices, edges, faces, textures, etc.
 */
 public class RenderRegistry extends gcore.Object {
	private ArrayList<gcore.Vector4d> vertexList=null;
	private ArrayList<Integer> lineList=null;
	
	 
	private int vertexCountHint=0;
	private int lineCountHint=0;
	private int spentLineCount=0;
	ArrayList<Integer> getLines(){
		return lineList;
	}
	ArrayList<gcore.Vector4d> getVertex(){
		return vertexList;
	}
	private static RenderRegistry defaultRegistor;
	public RenderRegistry() {
		/* @author sudip
		 * This is initial implementation
		 * We will have only one registor for initial build
		 * */
		RenderRegistry.defaultRegistor=this;
	}
	public void registerPoint(Point3d point){}
	public void registerPoint(Point3d[] points){}
	public void registerLine(Line3d line){}
	public void registerLine(Line3d[] line){}
	public int registerLine(int[] vector){
		int size=lineList.size();
		lineList.ensureCapacity(size+vector.length);
		for(int i:vector){
			lineList.add(i);
		}		
		return size; 
		//vertexList.addAll(spentVertexCount,Arrays.asList(vector));
	}
	public int registerLine(int start,int end){
		int size=lineList.size();
		lineList.add(start);
		lineList.add(end);
		return size;
	}
	public int registerVertex(gcore.Vector4d[] vector){
		int size=vertexList.size();
		vertexList.ensureCapacity(vertexList.size()+vector.length);
		vertexList.addAll(Arrays.asList(vector));
		return size;
	}
	public void rebuildArrays(){
		vertexList=new ArrayList<>(vertexCountHint);
		lineList=new ArrayList<>(lineCountHint);
	}
	public void registerTriangle(Triangle3d triangle){}
	public void registerTriangle(Triangle3d[] triangles){}
	public void registerQuad(Quad3d quad){}
	public void registerQuad(Quad3d[] quads){}
	public int registerVectorArray(){return 0;}
	public int registerColorArray(){return 0;}
	public void addVertexCountHint(int count){
		vertexCountHint+=count;
	}
	public static RenderRegistry getDefaultRegistor(){
		return RenderRegistry.defaultRegistor;
	}
}
 