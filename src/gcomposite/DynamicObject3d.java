package gcomposite;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Collection;

import com.sun.media.sound.InvalidFormatException;

import gcomposite.ObjReader.DataType;
import gcore.Object3d;

public class DynamicObject3d extends Object3d {

	ArrayList<Float> vertex;
	ArrayList<Integer> color;
	ArrayList <Integer> triangles;
	
		
	public DynamicObject3d (File _file)throws InvalidFormatException,FileNotFoundException,IOException{
		double[] vertex;
		int[] edge;
		
		ObjReader reader =new ObjReader(_file);
		ObjReader.DataType type;
		type=reader.readInitial();
		while(true){
			
			if(type==DataType.Vertex){
				vertex=reader.getVertex();
				System.out.println("Vertex :("+vertex[0]+", "+vertex[1]+", "+vertex[2]+")");
				this.vertex.add((float)vertex[0]);
				this.vertex.add((float)vertex[1]);
				this.vertex.add((float)vertex[2]);
			}
			else if(type==DataType.Face){
				edge=reader.getFace();
				System.out.println("Face: ("+edge[0]+", "+edge[1]+", "+edge[2]+")");
				this.triangles.add(edge[0]);
				this.triangles.add(edge[1]);
				this.triangles.add(edge[2]);
			}
			//else if(type==DataType.EndOfFile)
			else{
				System.out.println("Unknown type");
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		//reader.close();
	}
	
	@Override
	public void draw() {
		

	}

}
