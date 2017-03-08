package gcomposite;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.xml.internal.fastinfoset.algorithm.IEEE754FloatingPointEncodingAlgorithm;

import gcomposite.ObjReader.DataType;
import gcore.Object3d;

public class DynamicObject3d extends Object3d {

	ArrayList<double[]> vertex=new ArrayList<>();
	ArrayList<int[]> edge=new ArrayList<>();
	
	int[] color=null;
	
	ArrayList <int[]> triangles=new ArrayList<>();
	
	public DynamicObject3d (File _file)throws FileNotFoundException,IOException{
		double[] vertex;
		int[] edge;
		
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
				this.triangles.add(edge);
			}
		}
		reader.close();
		Random random=new Random();
		color=new int[this.triangles.size()];
		for(int i=0;i<triangles.size();i++){
			color[i]=0xff000000+random.nextInt()%0xffffff;
			
		}
	}
	@Override
	public void draw() {
		int offset=addVertex3(vertex.toArray(new double[0][0]));
		drawTriangle(triangles.toArray(new int[0][0]),color,offset);
		

	}

}
