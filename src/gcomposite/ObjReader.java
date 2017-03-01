package gcomposite;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ObjReader extends FileReader {
	public enum DataType{
		Vertex,
		TextureMap,
		VertexNormal,
		Face,
		Unknown
		
	}
	boolean lineRead;
	String readString;
	char[] buffer=new char[100];
	public ObjReader(File file) throws FileNotFoundException {
		super(file);
		
		// TODO Auto-generated constructor stub
	}
	boolean confirmRead(char _char) throws IOException{
		if(read()==_char)
			return true;
		return false;
	}
	void readLine() throws IOException{
		int i=0;
		char _char;
		while((_char=(char)read())!='\n'){
			buffer[i++]=_char;
		}
	}
	boolean readWord() throws IOException{
		char _char;
		int i=0;
		while(Character.isWhitespace(_char=(char)read()));
		buffer[i++]=_char;
		while(!Character.isWhitespace(_char=(char)read())){
			System.out.println("REad :"+_char);
			buffer[i++]=_char;
		}
		if(i==2)
			return false;
		return true;
	}
	DataType readInitial() throws IOException{
		if(!lineRead)
			readLine();
		lineRead=false;
		Character _char;
		while(true){
			if(readWord()){
				if(buffer[0]=='#'){
					readLine();
					continue;
				}
				else{
					return DataType.Unknown;
					//readLine();
				}
			}
			else{
				if(buffer[0]=='#'){
					readLine();
					continue;
				}
				else if(buffer[0]=='v'|| buffer[0]=='V'){
					return DataType.Vertex;
				}
				else if(buffer[0]=='f' || buffer[0]=='F'){
					return DataType.Face;
				}
				else{
					return DataType.Unknown;
					//readLine();
				}
			}
		}
		
	}
	double[] getVertex() throws IOException{
		lineRead=true;
		int start=0,count=0;
		readLine();
		double[] vertex=new double[3];
		
		
		for(;!Character.isWhitespace(buffer[count]);count++);
		vertex[0]=Double.valueOf(new String(buffer,start,count-start));
		for(;Character.isWhitespace(buffer[start]);start++);
		start+=count;
			
			
		for(;!Character.isWhitespace(buffer[start+count]);count++);
		vertex[1]=Double.valueOf(new String(buffer,start,count-start));
		for(;Character.isWhitespace(buffer[start]);start++);
		start+=count;
			
			
		for(;!Character.isWhitespace(buffer[start+count]);count++);
		vertex[1]=Double.valueOf(new String(buffer,start,count-start));
		
		return vertex;
	}
	int[] getFace() throws IOException{
		lineRead=true;
		int start=0,count=0;
		readLine();
		int[] vertex=new int[3];
		for(;buffer[count]!='\\' && buffer[count]!=0;count++);
		if(buffer[count]==0){
			count=start;
			for(;!Character.isWhitespace(buffer[count]);count++);
			vertex[0]=Integer.valueOf(new String(buffer,start,count-start));
			for(;Character.isWhitespace(buffer[start]);start++);
			start+=count;
					
					
			for(;!Character.isWhitespace(buffer[start+count]);count++);
			vertex[1]=Integer.valueOf(new String(buffer,start,count-start));
			for(;Character.isWhitespace(buffer[start]);start++);
			start+=count;
				
				
			for(;!Character.isWhitespace(buffer[start+count]);count++);
			vertex[1]=Integer.valueOf(new String(buffer,start,count-start));
		}
		else{
			count=start;
			for(;buffer[count]!='\\';count++);
			vertex[0]=Integer.valueOf(new String(buffer, start, count-start));
			for(;buffer[count]!='\\';count++);
			for(;!Character.isWhitespace(buffer[count]);count++);
			for(;Character.isWhitespace(buffer[start]);start++);
			start+=count;
			
			for(;buffer[count]!='\\';count++);
			vertex[0]=Integer.valueOf(new String(buffer, start, count-start));
			for(;buffer[count]!='\\';count++);
			for(;!Character.isWhitespace(buffer[count]);count++);
			for(;Character.isWhitespace(buffer[start]);start++);
			start+=count;
			
			for(;buffer[count]!='\\';count++);
			vertex[0]=Integer.valueOf(new String(buffer, start, count-start));
			for(;buffer[count]!='\\';count++);
		}
		
		
		
		
		return vertex;
	}
}
