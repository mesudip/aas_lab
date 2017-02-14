package gcore;

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
	int x1,y1,z1,x2,y2,z2,x3,y3,z3;
	int x,y,z;
	int dx1,dx2,xinc1,xinc2,dy,yinc;
	static int color;
	public static void setColor(int color) {
		TriangleRenderer.color = color;
	}
	int tmp;
	int i,i1;
	java.util.List<Float> vertex;
	java.util.List<Integer>tri;
	java.util.List<Integer>triColor;
	static Display display;
	public TriangleRenderer(java.util.List<Float> vertex,java.util.List<Integer>tri,java.util.List<Integer> colorArray) {
		this.vertex=vertex;
		this.tri=tri;
		triColor=colorArray;
	}
	public static void setDisplay(Display display) {
		TriangleRenderer.display = display;
	}
	public void testTriangle(){
		x1=0;y1=100;
		x2=600;y2=100;
		x3=600;y3=0;
	}
	public void rasterize(){
		int i1;
		for(int i=0;i<tri.size();){
			//System.out.println("Procesing tri vertex :"+i);
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
			color=triColor.get((i%3));
			//System.out.println("Triangle Rasterization :("+x1+", "+y1+"), ("+x2+", "+y2+"), ("+x3+", "+y3+")");
			if(y1==y2 && y2==y3)
				continue;
			if(x1 ==x2 && x2==x3)
				continue;
			
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
				if(x1==x2)
					continue;
				else if(x1>x2)
					renderFlatTriangle(x2, y2, z2, x1, z1, x3, y3, z3);
				
			}
			if(y2==y3){	//this is a up facing triangle with v1 up
				if(x2==x3)
					return;
				else if(x2<x3)
					renderFlatTriangle(x2, y2, z3, x3, z3, x1, y1, z1);
				else
					renderFlatTriangle(x3, y3, z3, x2, z2, x1, y1, z1);
			}
			//We dont' seem to be lucky.
			//Now we must divide triangle into two parts to make a up facing and a down facing triangle
			x=x1+(int)(((float)(y2-y1))/(y3-y1)*(x3-x1));
			if(x<x2){
				renderFlatTriangle(x, y2, z, x2, z2, x1, y1, z1);
				renderFlatTriangle(x, y2, z, x2, z2, x3, y3, z3);
			}
			else{
				renderFlatTriangle(x2, y2, z2, x, z, x1, y1, z1);
				renderFlatTriangle(x2, y2, z2, x, z, x3, y3, z3);
			}
		}
	}
	void renderFlatTriangle(int x1,int y1,int z1,int x2,int z2, int x3,int y3, int z3){
		//System.out.println("  Flat Triangle Rasterization :("+x1+", "+y1+"), ("+x2+", "+y1+"), ("+x3+", "+y3+")");
		//we have arranged the coordinates as: y3,y1=y2;
		dx1=x3-x1;
		dx2=x3-x2;
		dy=y3-y2;
		
		if(dx1<0)
			xinc1=-1;
		else 
			xinc1=+1;
		if(dx2<0)
			xinc2=-1;
		else 
			xinc2=+1;
		if(dy<0)
			yinc=-1;
		else
			yinc=+1;
		int p1,p2;
		dx1=Math.abs(dx1);dx2=Math.abs(dx2);dy=Math.abs(dy);
		
		
		if(dx1>dy){//case first line's sampling is aling x axis
			if(dx2>dy){//case second line's sampling is along x axis
				dy=dy<<1;
				p1=dy-dx1;
				p2=dy-dx2;
				dx1=dx1<<1;
				dx2=dx2<<1;
				display.drawLine(x1, y1, x2, y1,color);
			//	System.out.println("Drawline:"+x1+","+y1+"-"+x2+","+y1);
				while(y3!=y1){
					while(p1<0){  // for line 1
						x1+=xinc1;
						p1+=dy;
					}
					x1+=xinc1;
					p1-=dx1;
					p1+=dy;
					
					while(p2<0){	//for line 2
						x2+=xinc2;
						p2+=dy;
					}
					x2+=xinc2;
					p2-=dx2;
					p2+=dy;
					y1+=yinc;
					display.drawLine(x1, y1, x2, y1,color);
				//	System.out.println("Drawline:"+x1+","+y1+"-"+x2+","+y1);
				}
			}
			else{ //case second line's sampling is along y axis 
				dx2=dx2<<1;
				p2=dx2-dy;		;
				dy=dy<<1;
				p1=dy-dx1;
				dx1=dx1<<1;
				
				display.drawLine(x1, y1, x2, y1,color);
				//System.out.println("Drawline:"+x1+","+y1+"-"+x2+","+y1);
				while(y3!=y1){
					while(p1<0){  // for line 1
						x1+=xinc1;
						p1+=dy;
					}
					x1+=xinc1;
					p1-=dx1;
					p1+=dy;
					
					//for line 2
					p2+=dx2;
					if(p2>=0){
						x2+=xinc2;
						p2-=dy;
					}
					y1+=yinc;
					display.drawLine(x1, y1, x2, y1,color);
					//System.out.println("Drawline:"+x1+","+y1+"-"+x2+","+y1);
				}
			}
		}
		else{ //case first line's sampling is along y axis
			if(Math.abs(dx2)>Math.abs(dy)){// case 2nd line's sampling is along x axis
				dx1=dx1<<1;
				p1=dx1-dy;
				
				dy=dy<<1;
				
				p2=dy-dx2;
				dx2=dx2<<1;
				display.drawLine(x1, y1, x2, y1,color);
				//System.out.println("Drawline:"+x1+","+y1+"-"+x2+","+y1);
				while(y3!=y1){
					p1+=dx1;
					if(p1>=0){
						x1+=xinc1;
						p1-=dy;
					}
					
					while(p2<0){	//for line 2
						x2+=xinc2;
						p2+=dy;
					}
					x2+=xinc2;
					p2-=dx2;
					p2+=dy;
					y1+=yinc;
					display.drawLine(x1, y1, x2, y1,color);
					//System.out.println("Drawline:"+x1+","+y1+"-"+x2+","+y1);
				}
			}
			else{ //case 2nd line's sampling is along y axix
				dx2=dx2<<1;
				dx1=dx1<<1;
				p2=dx2-dy;		
				p1=dx1-dy;
				dy=dy<<1;
				display.drawLine(x1, y1, x2, y1,color);
				//System.out.println("Drawline:"+x1+","+y1+"-"+x2+","+y1);
				while(y3!=y1){
					p1+=dx1;
					if(p1>=0){
						x1+=xinc1;
						p1-=dy;
					}
					//for line 2
					p2+=dx2;
					if(p2>=0){
						x2+=xinc2;
						p2-=dy;
					}
					y1+=yinc;
					display.drawLine(x1, y1, x2, y1,color);
					//System.out.println("Drawline:"+x1+","+y1+"-"+x2+","+y1);
				}
			}
		}
		
	}
	void renderLine(){
		
	}
}
class LineRenderer{
	java.util.List<Float> vertex;
	java.util.List<Integer> lines;
	java.util.List<Integer> lineColor;
	static int activeColor;
	int x1,x2,y1,y2;
	int i,j;
	int dx,dy;
	int xinc,yinc;
	int p;
	float z1,z2;
	float zinc;
	static Display display;
	static void setDisplay(Display display){
		LineRenderer.display=display;
	}
	public LineRenderer(java.util.List<Float> vertex,java.util.List<Integer> edge,java.util.List<Integer> colorArray) {
		this.vertex=vertex;
		this.lines=edge;
		this.lineColor=colorArray;
	}
	public void rasterize(){
		for(int i=0;i<lines.size();i++){
			//System.out.print("  Line Render - Line ("+edge.get(i).toString());
			j=lines.get(i++);
			System.out.print(","+lines.get(i).toString()+") :");
			x1=(int)(float)vertex.get(j++);
			y1=(int)(float)vertex.get(j++);
			z1=vertex.get(j);
			j=lines.get(i);
			x2=(int)(float)vertex.get(j++);
			y2=(int)(float)vertex.get(j++);
			z2=vertex.get(j);
			//System.out.println("Coord  ("+String.valueOf(x1)+", "+String.valueOf(y1)+") to ("+String.valueOf(x2)+", "+String.valueOf(y2)+")");
			display.setColor(lineColor.get(i/2));
			dx=x2-x1;
			dy=y2-y1;
			
			if(dx<0)
				xinc=-1;
			else
				xinc=1;
			if(dy<0)
				yinc=-1;
			else
				yinc=1;
			zinc=z2-z1;
			zinc/=dx;
			
			dx=Math.abs(dx);dy=Math.abs(dy);
			if(dx>dy){
				dy=dy<<1;
				p=dy-dx;
				dx=dx<<1;
				
				display.drawPixel(x1, y1, z1);
				while(x1!=x2){
					z1+=zinc;
					if(p>=0){
						p-=dx;
						y1+=yinc;
					}
					x1+=xinc;
					p+=dy;
					//System.out.println("Pixel : "+x1+" ,"+y1);
					//System.out.println("Color :"+Integer.toString(activeColor,16));
					display.drawPixel(x1, y1, z1);
				}
			}
			else{
				dx=dx<<1;
				p=dx-dy;
				dy=dy<<1;
				display.drawPixel(x1, y1, z1);
				while(x1!=x2){
					z1+=zinc;
					if(p>=0){
						p-=dy;
						x1+=xinc;
					}
					y1+=yinc;
					p+=dx;
					//System.out.println("Pixel : "+x1+" ,"+y1);
					display.drawPixel(x1, y1, z1);
				}
				
			}
			//Display.getDisplay().drawLine(x1, y1, x2, y2,Color.BLACK.getRGB());
		
		}
	}
}
