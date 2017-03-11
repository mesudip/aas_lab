package gcomposite;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ObjReader extends DataInputStream {
	public enum DataType{
		Vertex,
		TextureMap,
		VertexNormal,
		Face,
		Unknown
		
	}
	boolean lineRead=true;;
	
	char[] buffer=new char[100];
	public ObjReader(File file) throws FileNotFoundException {
		super(new FileInputStream(file));
	}
	public DataType readInitial() throws IOException{
		String word=new String();
		char _read;
		while(!Character.isWhitespace(_read=readChar())){
			word+=_read;
		}
		if(word.equals("v")){
			return DataType.Vertex;
		}
		else if(word.equals("f")){
			return DataType.Face;
			
		}
		return DataType.Unknown;
	}
	double[] getVertex() throws IOException{
		System.out.println("new Vertex");
		double[] vertex=new double[3];
		vertex[0]=readDouble();
		vertex[1]=readDouble();
		vertex[2]=readDouble();
		return vertex;
	
	}
	int[] getFace() throws IOException{
		return new int[0];
	}
}
