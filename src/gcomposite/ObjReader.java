package gcomposite;


import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ObjReader extends DataInputStream {
	public enum DataType{
		Vertex,
		TextureMap,
		VertexNormal,
		Face,
		Unknown,
		EndOfFile;
		
	}
	boolean lineRead=true;;
	
	char[] buffer=new char[100];
	public ObjReader(File file) throws FileNotFoundException {
		super(new FileInputStream(file));
	}
	public DataType readInitial() throws IOException{
		
		String word=new String();
		int _read;
		while(true){
			_read=read();
			if(Character.isWhitespace((char)_read))
				break;
			if(_read==-1)
				return DataType.EndOfFile;
			word+=(char)_read;
		}
		System.out.println("Read initial is:"+word);
		if(word.equals("v")){
			return DataType.Vertex;
		}
		else if(word.equals("f")){
			return DataType.Face;
		}
		else if(word.equals("vn")){
			return DataType.VertexNormal;
		}
		readLine();
		return DataType.Unknown;
	}
	double[] getVertex() throws IOException{
		String line=readLine();
		
		String[] numbers=line.split("\\s");
		double[] vertex=new double[3];
		vertex[0]=Double.valueOf(numbers[0].trim());
		vertex[1]=Double.valueOf(numbers[1].trim());
		vertex[2]=Double.valueOf(numbers[2].trim());
		return vertex;
	}
	
	int[][] getFace() throws IOException{
		String line=readLine();
		String[] numbers=line.split("\\s");
		int[][] face=new int[3][3];
		String[] indices;
		
		indices=numbers[0].trim().split("\\/");
		face[0][0]=Integer.valueOf(indices[0])-1;
		face[1][0]=Integer.valueOf(indices[1])-1;
		face[2][0]=Integer.valueOf(indices[2])-1;
		
		indices=numbers[1].trim().split("\\/");
		face[0][1]=Integer.valueOf(indices[0])-1;
		face[1][1]=Integer.valueOf(indices[1])-1;
		face[1][2]=Integer.valueOf(indices[2])-1;		
		
		indices=numbers[2].trim().split("\\/");
		face[0][2]=Integer.valueOf(indices[0])-1;
		face[1][2]=Integer.valueOf(indices[1])-1;
		face[2][2]=Integer.valueOf(indices[2])-1;
		return face;
	}
	public double[] getVertexNormal() throws IOException{
		String line=readLine();
		
		String[] numbers=line.split("\\s");
		double[] vertexNormal=new double[3];
		vertexNormal[0]=Double.valueOf(numbers[0].trim());
		vertexNormal[1]=Double.valueOf(numbers[1].trim());
		vertexNormal[2]=Double.valueOf(numbers[2].trim());
		return vertexNormal;
	}
}
						