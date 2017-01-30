package gcore;

import java.util.EventListenerProxy;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class PrimitiveRender {
	/**
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param x3
	 * @param y3
	 * Operations of function ->
	 * Divide a triangle into two parts with the help of a horizontal line thourgh one of the vertices
	 * Now there are two triangles one pointed up and other pointed down.
	 * Yeh! we have changed so many types of triangles into only two types.
	 */
}
class TriangleRenderer{
	float x1,y1,z1,x2,y2,z2,x3,y3,z3;
	int color;
	float tmp;
	int i,i1;
	java.util.List<Float> vertex;
	java.util.List<Integer>tri;
	void renderTriangle_crazy(){
		
		Display display=Display.getDisplay();
		int i1;
		for(int i=0;i<tri.size();i++){
			// First order the vertices by the y values (might also be equal some times)
			i1=tri.get(i++);
			x1=(int)(float)vertex.get(i1++);
			y1=(int)(float)vertex.get(i1++);
			z1=(int)(float)vertex.get(i1);
			i1=tri.get(i++);
			x2=(int)(float)vertex.get(i1++);
			y2=(int)(float)vertex.get(i1++);
			z2=(int)(float)vertex.get(i1);
			i1=tri.get(i++);
			x3=(int)(float)vertex.get(i1++);
			y3=(int)(float)vertex.get(i1++);
			z3=(int)(float)vertex.get(i1);
			
			if(y2>y3){
				if(y1<y2){
					if(y1<y3){	//condition y2>y3>y1
						tmp=y1;y1=y2;y2=y3;y3=tmp;
						tmp=x1;x1=x2;x2=x3;x3=tmp;
					}
					else{	//condition y2>y1>y3
						tmp=y2;y2=y1;y1=tmp;
						tmp=x2;x2=x1;x1=tmp;
						
					}
					
				}
				else{ //condition y1>y2>y3
					//need to nothing in this case.
					
				}
			}
			else{
				if(y2>y1){//condition y3>y2>y1
					tmp=y1;y1=y3;y3=tmp;
					tmp=x1;x1=x3;x3=tmp;
				}
				else{
					if(y3>y1){ //condition y3>y1>y2
						tmp=y3;y1=y3;y3=y2;y2=tmp;
						tmp=x3;x1=x3;x3=x2;x2=tmp;
					}
					else{  //condition y1>y3>y2
						tmp=y3;y3=y2;y2=tmp;
						tmp=x3;x3=x2;x2=tmp;
					}
				}
			}
			
			//That was some crazy code above.
			//Now we have ordered the values such that: y1 >= y2 >= y3
			
			
			if(y1==y2){ //This means the triangle is below facing 
				if(y2==y3)
						return;// This is not a triangle. It is a line
					
				else{	//this is a downward facing triangle with v3 downward
					if(x1==x2)
						return;
					else if(x1<x2)
						renderTriangleDownPointing(x1, y1, z1, x2, y2, z2, x3,y3,z3);
					else
						renderTriangleDownPointing(x2, y2, z2, x1, y1, z1, x3, y3, z3);
					
				}
			}
			if(y2==y3){	//this is a up facing triangle with v1 up
				if(x2==x3)
					return;
				else if(x2<x3)
					renderTriangleUpPointing(x2, y2, z3, x3, y3, z3, x1, y1, z1);
				else
					renderTriangleUpPointing(x3, y3, z3, x2, y2, z2, x1, y1, z1);
			}
			
			if(x1==x2&&x2==x3){ //if the triangle forms a horizontal line
				return;
			}
			//We dont' seem to be lucky.
			//Now we must divide triangle into two parts to make a up facing and a down facing triangle
		}
	}
	void renderTriangleUpPointing(float x1,float y1,float z1,float x2,float y2,float z2,float x3,float y3,float z3){
		
		
	}
	void renderTriangleDownPointing(float x1,float y1,float z1,float x2,float y2,float z2,float x3,float y3,float z3){
		
	}
}
