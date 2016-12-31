package rendercore;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import com.sun.xml.internal.fastinfoset.algorithm.IEEE754FloatingPointEncodingAlgorithm;

import gcore.Object3d;
import gcore.Transform;
import gcore.Vector4d;
import gprimitive.*;

/**
 * @author sudip
 * Date : Dec 29, 2016
 * -->
 * This class will be the registry point.
 * All objects will give hint about their memory use
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
	public void drawPoint(Point3d point){}
	public void drawPoint(Point3d[] points){}
	public void drawLine(Line3d[] line){}
	public int drawLine(int[] vector){
		int size=lineList.size();
		for(int i:vector){
			lineList.add(i);
		}		
		return size; 
		//vertexList.addAll(spentVertexCount,Arrays.asList(vector));
	}
	public int drawLine(int offset,int[] vector){
		int size=lineList.size();
		
		for( int i:vector){
			lineList.add(i+offset);
		}
		return size;
	}
	public int drawLine(int start,int end){
		int size=lineList.size();
		lineList.add(start);
		lineList.add(end);
		return size;
	}
	public int registerVector(gcore.Vector4d[] vector){
		int size=vertexList.size();
		vertexList.ensureCapacity(vertexList.size()+vector.length);
		vertexList.addAll(Arrays.asList(vector));
		return size;
	}
	public void rebuildArrays(){
		vertexList=new ArrayList<>(vertexCountHint);
		lineList=new ArrayList<>(lineCountHint);
	}
	public java.util.List <Vector4d> getVectorList(int start,int count){
		return vertexList.subList(start, start+count); 
	}
	public void useTransform(int start,int count,gcore.Transform transform){
		transform.applyOn(vertexList.subList(start, start+count));
	}
	public void drawTriangle(Triangle3d triangle){}
	public void drawTriangle(Triangle3d[] triangles){}
	public void drawQuad(Quad3d quad){}
	public void drawQuad(Quad3d[] quads){}
	public int drawVectorArray(){return 0;}
	public int registerColorArray(){return 0;}
	public void addVertexCountHint(int count){
		vertexCountHint+=count;
	}
	public void addLineCountHint(int count){
		lineCountHint+=count;
	}
	public static RenderRegistry getDefaultRegistor(){
		return RenderRegistry.defaultRegistor;
	}
}
 