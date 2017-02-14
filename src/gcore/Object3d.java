package gcore;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.renderable.RenderableImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.NEW;

import gprimitive.Line;
public abstract class Object3d extends Object implements Transformable, Drawable{
	
	static private List<Object3d> object=new ArrayList<Object3d>();
	static private List<Float> vertex;
	static private List<Integer> edge;
	static private List<Integer> face;
	static private List<Integer> tri;
	static private int vertexHint;
	static private int edgeHint;
	static private int faceHint;
	static protected int activeColor;
	public gcore.Transform transform=new Transform();
	static class __System{
		public PrintStream out=java.lang.System.out;
		private PrintStream devNull;
		public __System(){
			try{
				devNull=new PrintStream(new File("/dev/null"));
			}catch(FileNotFoundException e){
				
			}
		}
		public void disablePrint(){
			out=devNull;
		}
		public void enablePrint(){
			out=java.lang.System.out;	
		}
		
	}
	static __System System=new __System();
	public Object3d(){
		object.add(this);
		transform.loadIdentity();
	}
	
	protected void setPosition(float x,float y,float z){
		transform.x=x;
		transform.y=y;
		transform.z=z;
	}
	static public void makeArrays(){
		vertex=new ArrayList<Float>(vertexHint);
		edge=new ArrayList<Integer>(edgeHint);
		face=new ArrayList<Integer>(faceHint);
	}
	protected int addVertex(float x1,float y1,float z1){
		int count=vertex.size();
		vertex.add(x1);
		vertex.add(y1);
		vertex.add(z1);
		vertex.add((float) 1.0);
		return count;
	}
	protected int addVertex3(float[] table){
		int size=vertex.size();
		for(int i=0;i<table.length;){
			vertex.add(table[i++]);
			vertex.add(table[i++]);
			vertex.add(table[i++]);
			vertex.add((float)1.0);
		}
		return size;
	}
	protected void drawLine(float x1,float y1,float z1,float x2,float y2,float z2 ){
		edge.add(addVertex(x1, y1, z1));
		edge.add(addVertex(x2,y2, z2));
		
	}
	protected void drawLine(int offset1,int offset2){
		edge.add(offset1);
		edge.add(offset2);
	}
	protected void drawLine(int[] line,int offset){
		for(int index:line){
			edge.add((index<<2)+offset);
		}
	}
	
	protected void drawFace(){
		
	}
	
	public gcore.Transform getTransform(){
		return transform;
	}
	
	
	private static void renderLines(){
		int x1,y1,x2,y2;int i1,i2;
		for(int i=0;i<edge.size();i++){
			
			System.out.print("  Line Render - Line ("+edge.get(i).toString());
			i1=edge.get(i++);
			System.out.print(","+edge.get(i).toString()+") :");
			x1=(int)(float)vertex.get(i1++);
			y1=(int)(float)vertex.get(i1);
			i2=edge.get(i);
			x2=(int)(float)vertex.get(i2++);
			y2=(int)(float)vertex.get(i2);
			System.out.println("Coord  ("+String.valueOf(x1)+", "+String.valueOf(y1)+") to ("+String.valueOf(x2)+", "+String.valueOf(y2)+")");
			Display.getDisplay().drawLine(x1, y1, x2, y2,Color.BLACK.getRGB());
		
		}
	}
	static int frameCount=0;
	static public  void render(){
		
		System.out.println("Frame ["+String.valueOf(frameCount)+"] : Render Start");
		makeArrays();//new array for storing vertices;
		int lastOffset;
		for(Object3d object3d :object){
			System.out.println(" Drawing :"+object3d.toString());
			lastOffset=vertex.size();
			object3d.draw();//draw call will register the lines and vertices
			object3d.transform.applyOn(vertex.subList(lastOffset,vertex.size()));//apply the object's modelview transform
		}
		Camera.getCamera().applyTransforms(vertex);
		renderLines();
		System.out.println("Frame ["+String.valueOf(frameCount++)+"] : Render End\n");
	}
	static public void setActiveColor(int color){
		activeColor=color;
	}
	static public void printVertices(){
		for(int i=0;i<vertex.size();){
			System.out.print('(');
			System.out.printf("%.2f",vertex.get(i++));
			System.out.print(",\t");
			System.out.printf("%.2f",vertex.get(i++));
			System.out.print(",\t");
			System.out.printf("%.2f",vertex.get(i++));
			System.out.print(",\t");
			System.out.printf("%.2f",vertex.get(i++));
			System.out.println(')');	
		}
	}
	static public void printEdges(){
		for(int i=0;i<edge.size();i++){
			System.out.print(edge.get(i++));
			System.out.print(",\t");
			System.out.println(edge.get(i));
		}
	}
	static public void noLog(){
		System.disablePrint();
	}
	static public void enableLog(){
		System.enablePrint();
	}
	

}

