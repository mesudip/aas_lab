package gcomposite;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import gcomposite.ObjReader.DataType;
import gcore.Object3d;
import gmath.Vector;
public class DynamicObject3d extends Object3d {

	ArrayList<double[]> vertex=new ArrayList<>();
	///ArrayList<int[][]> edge=new ArrayList<>();
	ArrayList<double[]> vertexNormal=new ArrayList<>();
	ArrayList<int[]> triangularFaces=new ArrayList<>();
	int[] color=null;
	
	public DynamicObject3d (File _file)throws FileNotFoundException,IOException{
		ArrayList <int[][]> triangles=new ArrayList<>();
		double[] vertex;
		int[][] edge;
		
		ObjReader reader =new ObjReader(_file);
		ObjReader.DataType type=DataType.Vertex;
		while((type=reader.readInitial())!=DataType.EndOfFile){
			if(type==DataType.Vertex){
				vertex=reader.getVertex();
				System.out.println("Vertex :("+vertex[0]+", "+vertex[1]+", "+vertex[2]+")");
				this.vertex.add(vertex);
			}
			else if(type==DataType.Face){
				edge=reader.getFace();
				System.out.println("Face: ("+edge[0]+", "+edge[1]+", "+edge[2]+")");
				triangles.add(edge);
			}
			else if(type==DataType.VertexNormal){
				vertex=reader.getVertexNormal();
				System.out.println("Vertex Normal:("+vertex[0]+", "+vertex[1]+", "+vertex[2]+")");
				vertexNormal.add(vertex);
			}	
		}
		reader.close();
		
		System.out.println("Size of vertex Array:"+this.vertex.size());
		System.out.println("Size of normal Array:"+this.vertexNormal.size());
		System.out.println("Size of triangle array:"+triangles.size());
		
		// now make normal consistent.
		for (int[][] index : triangles) {
			double[] normal=Vector.getTriangleNormal(this.vertex.get(index[0][0]),this.vertex.get(index[0][1]), this.vertex.get(index[0][2]));
			double[] given_normal=this.vertexNormal.get(index[2][0]);
			if((normal[0]*given_normal[0]+normal[0]*given_normal[0]+normal[0]*given_normal[0])<0){
				int temp=index[0][0];
				index[0][0]=index[0][2];
				index[0][2]=temp;
			}
		}
		
		Random random=new Random();
		color=new int[triangles.size()];
		for(int i=0;i<triangles.size();i++){
			color[i]=0xff000000+random.nextInt()%0xffffff;
			
		}
		double[][] averege_vertex_noral=new double[this.vertexNormal.size()][3];
		for (int[][] index : triangles) {
			
				if(averege_vertex_noral[index[2][0]]!=null){
				averege_vertex_noral[index[2][0]][0]+=this.vertexNormal.get(index[2][0])[0];
				averege_vertex_noral[index[2][0]][1]+=this.vertexNormal.get(index[2][0])[1];
				averege_vertex_noral[index[2][0]][2]+=this.vertexNormal.get(index[2][0])[2];
			}
			else{
				double[] new_normal=new double[3];
				new_normal[0]=this.vertexNormal.get(index[2][0])[0];
				new_normal[1]=this.vertexNormal.get(index[2][0])[1];
				new_normal[2]=this.vertexNormal.get(index[2][0])[2];
				averege_vertex_noral[index[2][0]]=new_normal;
			}
		}
		
		this.vertexNormal=new ArrayList<double[]>();
		
		java.util.Collections.addAll(vertexNormal,averege_vertex_noral);
		
		System.out.println("the output normal values for each vertex are :");
		for (double[] vector : averege_vertex_noral) {
			Vector.printVector(vector);
		}
		for (int[][] index : triangles) {
			triangularFaces.add(index[0]);
		}
	}
	@Override
	public void draw() {
		int offset=addVertex3(vertex.toArray(new double[0][0]));
		drawTriangle(triangularFaces.toArray(new int[0][0]),color,offset);
			}

}
