package gcore;
import java.io.PrintStream;
import java.lang.Math;
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
	static class System{
		public static PrintStream out=java.lang.System.out;
	}
	void setLogStream(PrintStream stream){
		System.out=stream;
	}
	
	int x1,y1,x2,y2,x3,y3;
	float z1,z2,z3,dz1,dz2;
	int x,y;
	float z;
	int p1,p2;
	int dx1,dx2,xinc1,xinc2,dy1,dy2,yinc,dy;
	static int color;
	int counter;
	public float [] camera_forward;
	public static void setColor(int color) {
		TriangleRenderer.color = color;
	}
	int tmp;float ftemp;
	int i,i1;
	java.util.List<Float> vertex;
	java.util.List<Integer>tri;
	java.util.List<Integer>triColor;
	static Display display;
	LineRenderer lineRenderer;
	public TriangleRenderer(java.util.List<Float> vertex,java.util.List<Integer>tri,java.util.List<Integer> colorArray) {
		this.vertex=vertex;
		this.tri=tri;
		triColor=colorArray;
		lineRenderer=new LineRenderer(null,null,null);
	}
	public static void setDisplay(Display display) {
		TriangleRenderer.display = display;
	}
	public void testTriangle(){
		x1=0;y1=100;
		x2=600;y2=100;
		x3=600;y3=0;
	}
	public void _rasterize(){
		int i1;
		for(int color:triColor){
			//System.out.print(", "+Integer.valueOf(i1))
		}
		System.out.println("The size of tri just before for  loop:"+tri.size());
		int i=0;
		for(;i<tri.size();){
			System.out.println("Procesing tri :"+i);
			color=triColor.get(i/3);
			i1=tri.get(i++);
			x1=Math.round(vertex.get(i1++));
			y1=Math.round(vertex.get(i1++));
			z1=vertex.get(i1);
			i1=tri.get(i++);
			x2=Math.round(vertex.get(i1++));
			y2=Math.round(vertex.get(i1++));
			z2=vertex.get(i1);
			i1=tri.get(i++);
			x3=Math.round(vertex.get(i1++));
			y3=Math.round(vertex.get(i1++));
			z3=vertex.get(i1);
			
			//calculating surface normal
			z=(y2-y1)*(z3-z1)-(y3-y1)*(z2-z1);
			dz1=(z2-z1)*(x3-x1)-(x2-x1)*(z3-z1);
			dz2=(x2-x1)*(y3-y1)-(x3-x1)*(y2-y1);
			if((z*camera_forward[0]+dz1*camera_forward[1]+dz2*camera_forward[2])>0){
				//System.out.println("Backface Detected");
				
			}
			
			//display.setColor(0xffffff00);
			display.setColor(color);
			
			System.out.println("Triangle Rasterization :("+x1+", "+y1+", "+z1+"), ("+x2+", "+y2+", "+z1+"), ("+x3+", "+y3+", "+z3+")");
			if(y1==y2 && y2==y3)
				continue;
			if(x1 ==x2 && x2==x3)
				continue;	
			
			if(y2>y3){
				if(y1<y2){
					if(y1<y3){	//condition y2>y3>y1
						tmp=y1;y1=y2;y2=y3;y3=tmp;
						tmp=x1;x1=x2;x2=x3;x3=tmp;
						ftemp=z1;z1=z2;z2=z3;z3=ftemp;
					}
					else{	//condition y2>y1>y3
						tmp=y2;y2=y1;y1=tmp;
						tmp=x2;x2=x1;x1=tmp;
						ftemp=z2;z2=z1;z1=ftemp;
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
					ftemp=z1;z1=z3;z3=ftemp;
				}
				else{
					if(y3>y1){ //condition y3>y1>y2
						tmp=y3;y3=y2;y2=y1;y1=tmp;
						tmp=x3;x3=x2;x2=x1;x1=tmp;
						ftemp=z3;z3=z2;z2=z1;z1=ftemp;
					}
					else{  //condition y1>y3>y2
						tmp=y3;y3=y2;y2=tmp;
						tmp=x3;x3=x2;x2=tmp;
						ftemp=z3;z3=z2;z2=ftemp;
					}
				}
			}
			//That was some crazy code above.
			//Now we have ordered the values such that: y1 >= y2 >= y3
			if(y1==y2){ //This means the triangle is below facing 
				
				if(x1==x2) //it's a line
					continue;
				else if(x1>x2)
					renderFlatTriangle(x2, y2, z2, x1, z1, x3, y3, z3);
				
			}
			if(y2==y3){	//this is a up facing triangle with v1 up
				if(x2==x3)// it's a ine
					continue;
				else if(x2<x3)
					renderFlatTriangle(x2, y2, z2, x3, z3, x1, y1, z1);
				else
					renderFlatTriangle(x3, y3, z3, x2, z2, x1, y1, z1);
			}
			//We dont' seem to be lucky.
			//Now we must divide triangle into two parts to make a up facing and a down facing triangle
			x=x1+Math.round((((float)(y2-y1))/(y3-y1)*(x3-x1)));
			if(z3==z1)
				z=z3;
			else{
				z=z1+(((float)(y2-y1))/(y3-y1))*(z3-z1);
			}
			//calculation of z is done by using the condition for four points to be in same plane.
			//System.out.println("Divided part: ("+x+", "+y2+", "+z);
			if(x<x2){
				renderFlatTriangle(x, y2, z, x2, z2, x1, y1, z1);
				renderFlatTriangle(x, y2, z, x2, z2, x3, y3, z3);
			}
			else{
				renderFlatTriangle(x2, y2, z2, x, z, x1, y1, z1);
				renderFlatTriangle(x2, y2, z2, x, z, x3, y3, z3);
			}
		}
		System.out.println("The value of i after the loop is:"+i);
	}
	public void rasterize(){
		int i1;
		for(int color:triColor){
			//System.out.print(", "+Integer.valueOf(i1))
		}
		System.out.println("The size of tri just before for  loop:"+tri.size());
		int i=0;
		for(;i<tri.size();){
			System.out.println("Procesing tri :"+i);
			color=triColor.get(i/3);
			i1=tri.get(i++);
			x1=Math.round(vertex.get(i1++));
			y1=Math.round(vertex.get(i1++));
			z1=vertex.get(i1);
			i1=tri.get(i++);
			x2=Math.round(vertex.get(i1++));
			y2=Math.round(vertex.get(i1++));
			z2=vertex.get(i1);
			i1=tri.get(i++);
			x3=Math.round(vertex.get(i1++));
			y3=Math.round(vertex.get(i1++));
			z3=vertex.get(i1);
			
			//calculating surface normal
			z=(y2-y1)*(z3-z1)-(y3-y1)*(z2-z1);
			dz1=(z2-z1)*(x3-x1)-(x2-x1)*(z3-z1);
			dz2=(x2-x1)*(y3-y1)-(x3-x1)*(y2-y1);
			if((z*camera_forward[0]+dz1*camera_forward[1]+dz2*camera_forward[2])>0){
				//System.out.println("Backface Detected");
				
			}
			
			//display.setColor(0xffffff00);
			display.setColor(color);
			
			if(y1==y2 && y2==y3)
				continue;
			if(x1 ==x2 && x2==x3)
				continue;	
			
			if(y2>y3){
				if(y1<y2){
					if(y1<y3){	//condition y2>y3>y1
						tmp=y1;y1=y2;y2=y3;y3=tmp;
						tmp=x1;x1=x2;x2=x3;x3=tmp;
						ftemp=z1;z1=z2;z2=z3;z3=ftemp;
					}
					else{	//condition y2>y1>y3
						tmp=y2;y2=y1;y1=tmp;
						tmp=x2;x2=x1;x1=tmp;
						ftemp=z2;z2=z1;z1=ftemp;
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
					ftemp=z1;z1=z3;z3=ftemp;
				}
				else{
					if(y3>y1){ //condition y3>y1>y2
						tmp=y3;y3=y2;y2=y1;y1=tmp;
						tmp=x3;x3=x2;x2=x1;x1=tmp;
						ftemp=z3;z3=z2;z2=z1;z1=ftemp;
					}
					else{  //condition y1>y3>y2
						tmp=y3;y3=y2;y2=tmp;
						tmp=x3;x3=x2;x2=tmp;
						ftemp=z3;z3=z2;z2=ftemp;
					}
				}
			}
			//That was some crazy code above.
			//Now we have ordered the values such that: y1 >= y2 >= y3
			yinc=-1;
			System.out.println("Triangle Rasterization :("+x1+", "+y1+", "+z1+"), ("+x2+", "+y2+", "+z1+"), ("+x3+", "+y3+", "+z3+")");
			accurateTriangleRenderer();
		}
	}

	void renderFlatTriangle(int x1,int y1,float z1,int x2,float z2, int x3,int y3, float z3){
		//System.out.println("  Flat Triangle Rasterization :("+x1+", "+y1+"), ("+x2+", "+y1+"), ("+x3+", "+y3+")");
		//we have arranged the coordinates as: y3,y1=y2;
		dx1=x3-x1;
		dx2=x3-x2;
		dz1=z3-z1;
		dz2=z3-z2;
		
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
		dz1/=dy;
		dz2/=dy;
		
		if(dx1>dy){//case first line's sampling is aling x axis
			
			if(dx2>dy){//case second line's sampling is along x axis
				
				dy=dy<<1;
				p1=dy-dx1;
				p2=dy-dx2;
				dx1=dx1<<1;
				dx2=dx2<<1;
				lineRenderer.renderHorizontalLine(y1, x1, x2, z1, z2);
			//	System.out.println("Drawline:"+x1+","+y1+"-"+x2+","+y1);
				while(y3!=y1){ 
					
					while(p1<0){  // continue following the line until you get out of current y. 
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
					
					z1+=dz1;z2+=dz2;
					lineRenderer.renderHorizontalLine(y1, x1, x2, z1, z2);
				//	System.out.println("Drawline:"+x1+","+y1+"-"+x2+","+y1);
				}
			}
			else{ //case second line's sampling is along y axis 
				dx2=dx2<<1;
				p2=dx2-dy;		;
				dy=dy<<1;
				p1=dy-dx1;
				dx1=dx1<<1;
				lineRenderer.renderHorizontalLine(y1, x1, x2, z1, z2);
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
					z1+=dz1;z2+=dz2;
					lineRenderer.renderHorizontalLine(y1, x1, x2, z1, z2);
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
				lineRenderer.renderHorizontalLine(y1, x1, x2, z1, z2);
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
					
					z1+=dz1;z2+=dz2;
					lineRenderer.renderHorizontalLine(y1, x1, x2, z1, z2);
					//System.out.println("Drawline:"+x1+","+y1+"-"+x2+","+y1);
				}
			}
			else{ //case 2nd line's sampling is along y axix
				dx2=dx2<<1;
				dx1=dx1<<1;
				p2=dx2-dy;		
				p1=dx1-dy;
				dy=dy<<1;
				lineRenderer.renderHorizontalLine(y1, x1, x2, z1, z2);
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
					z1+=dz1;z2+=dz2;
					lineRenderer.renderHorizontalLine(y1, x1, x2, z1, z2);
					//System.out.println("Drawline:"+x1+","+y1+"-"+x2+","+y1);
				}
			}
		}
		
	}
	
	void accurateTriangleRenderer(){
		/**
		 * The vertices are arranged such that:
		 * y1>=y2>=y3
		*/
		dx1=x3-x1;
		dx2=x2-x1;
		          	
		dy1=y3-y1;
		dy2=y2-y1;
		
		dz1=z3-z1;
		dz2=z2-z1;
		
		if(dx1<0)
			xinc1=-1;
		else
			xinc1=1;
		if(dx2<0)
			xinc2=-1;
		else 
			xinc2=1;
	
		dx1=Math.abs(dx1);
		dx2=Math.abs(dx2);
		dy1=Math.abs(dy1);
		dy2=Math.abs(dy2);
		
		dz1/=dy1;
		dz2/=dy2;
		//int counter=dy2;
		int back_x2=x2;
		float back_z2=z2;
		x2=x1;
		z2=z1;
		
			if(dx1>dy1){//case first line's sampling is along x axis
				dy1=dy1<<1;
				p1=dy1-dx1;
				dx1=dx1<<1;
				
				accurateTriangleRenderer2_1();
				x2=back_x2;
				z2=back_z2;
				
				dx2=x3-x2;
				if(dx2<0)
					xinc2=-1;
				else
					xinc2=1;
				dy2=y3-y2;
				dz2=z3-z2;
				y2=y3;
				dx2=Math.abs(dx2);
				dy2=Math.abs(dy2);
				
				dz2/=dy2;
				
				accurateTriangleRenderer2_1();
			}
			else{ //case first line's sampling is along y axis
				dx1=dx1<<1;
				p1=dx1-dy1;
				dy1=dy1<<1;
				accurateTriangleRenderer2_2();
				x2=back_x2;
				z2=back_z2;
				dx2=x3-x2;
				if(dx2<0)
					xinc2=-1;
				else
					xinc2=1;
				dy2=y3-y2;
				dz2=z3-z2;
				y2=y3;
				
				dx2=Math.abs(dx2);
				dy2=Math.abs(dy2);
				dz2/=dy2;
				accurateTriangleRenderer2_2();
			}
	}	
	private void accurateTriangleRenderer2_1() {//case that first line is sampled along x axis
		
		if(dx2>dy2){//case second line's sampling is along x axis
			//counter=dy2;
			dy2=dy2<<1;
			p2=dy2-dx2;
			dx2=dx2<<1;
			System.out.println("Internal Renderer 2_1_dx2>dy2");
			lineRenderer.renderHorizontalLine(y1, x1, x2, z1, z2);
			
			while(y2!=y1){ 
	
				while(p1<0){  // continue following the line until you get out of current y. 
					x1+=xinc1;
					p1+=dy1;
				}
				x1+=xinc1;
				p1-=dx1;
				p1+=dy1;
				
				while(p2<0){	//for line 2
					x2+=xinc2;
					p2+=dy2;
				}
				
				x2+=xinc2;
				p2-=dx2;
				p2+=dy2;
				y1+=yinc;
				
				z1+=dz1;z2+=dz2;
				lineRenderer.renderHorizontalLine(y1, x1, x2, z1, z2);
			//	System.out.println("Drawline:"+x1+","+y1+"-"+x2+","+y1);
			}
		}
		else{ //case second line's sampling is along y axis
			System.out.println("Internal Renderer 2_1_dx2<dy2");
			//counter=dy2;
			dx2=dx2<<1;
			p2=dx2-dy2;
			dy2=dy2<<1;
			lineRenderer.renderHorizontalLine(y1, x1, x2, z1, z2);
			//System.out.println("Drawline:"+x1+","+y1+"-"+x2+","+y1);
			while(y2!=y1){
				while(p1<0){  // for line 1 continue until y increases
					x1+=xinc1;
					p1+=dy1;
				}
				x1+=xinc1;
				p1-=dx1;p1+=dy1;
				
				// But for line 2, y increases each time. x may or may not increase.
				p2+=dx2;
				if(p2>=0){
					x2+=xinc2;
					p2-=dy2;
				}
				y1+=yinc;
				
				z1+=dz1;z2+=dz2;
				
				lineRenderer.renderHorizontalLine(y1, x1, x2, z1, z2);
				//System.out.println("Drawline:"+x1+","+y1+"-"+x2+","+y1);
			}
		}

	}
	private void accurateTriangleRenderer2_2(){
		if(dx2>dy2){// case 2nd line's sampling is along x axis
			System.out.println("Internal Renderer 2_2_dx2>dy2");
			p2=dy2-dx2;
			dx2=dx2<<1;
			dy2=dy2<<1;
			
			lineRenderer.renderHorizontalLine(y1, x1, x2, z1, z2);
			while(y2!=y1){
				p1+=dx1;
				if(p1>=0){
					x1+=xinc1;
					p1-=dy1;
				}
				
				while(p2<0){	//for line 2
					x2+=xinc2;
					p2+=dy2;
				}
				x2+=xinc2;
				p2-=dx2;
				p2+=dy2;
				y1+=yinc;
				
				z1+=dz1;z2+=dz2;
				lineRenderer.renderHorizontalLine(y1, x1, x2, z1, z2);
				//System.out.println("Drawline:"+x1+","+y1+"-"+x2+","+y1);
			}
		}
		else{ //case 2nd line's sampling is along y axix
			
			System.out.println("Internal Renderer 2_2_dx2<dy2");
			dx2=dx2<<1;
			p2=dx2-dy2;
			dy2=dy2<<1;
			
			lineRenderer.renderHorizontalLine(y1, x1, x2, z1, z2);
			//System.out.println("Drawline:"+x1+","+y1+"-"+x2+","+y1);
			while(y2!=y1){
				p1+=dx1;
				if(p1>=0){
					x1+=xinc1;
					p1-=dy1;
				}
				//for line 2
				p2+=dx2;
				if(p2>=0){
					x2+=xinc2;
					p2-=dy2;
				}
				y1+=yinc;	
				z1+=dz1;z2+=dz2;
				lineRenderer.renderHorizontalLine(y1, x1, x2, z1, z2);
				//System.out.println("Drawline:"+x1+","+y1+"-"+x2+","+y1);
			}
		}
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
			j=lines.get(i++);
			x1=Math.round(vertex.get(j++));
			y1=Math.round(vertex.get(j++));
			z1=vertex.get(j);
			j=lines.get(i);
			x2=Math.round(vertex.get(j++));
			y2=Math.round(vertex.get(j++));
			z2=vertex.get(j);
			 //System.out.println("Coord  ("+String.valueOf(x1)+", "+String.valueOf(y1)+") to ("+String.valueOf(x2)+", "+String.valueOf(y2)+")");
			 //try{Thread.sleep(2000);}catch(Exception e){}
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
			dx=Math.abs(dx);dy=Math.abs(dy);
			if(dx>dy){
				zinc/=dx;
				dy=dy<<1;
				p=dy-dx;
				y2=dx;
				dx=dx<<1;
				
				display.drawPixel(x1, y1, z1);
				for(j=0;j<y2;j++){
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
				zinc/=dy;
				dx=dx<<1;
				p=dx-dy;
				x2=dy;
				dy=dy<<1;
				display.drawPixel(x1, y1, z1);
				for(j=0;j<x2;j++){
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
	public void renderHorizontalLine(int y,int x1,int x2,float z1,float z2){
		//System.out.println("Horizontal line : ("+x1+", "+y+", "+z1+"), ("+x2+", "+y+", "+z2+")");
		if(x2<x1){
			int tmp;
			tmp=x2;x2=x1;x1=tmp;
			float _tmp;
			_tmp=z1;z1=z2;z2=_tmp;
		}
		if(x1==x2){
			display.drawPixel(x1, y, z1);
			display.drawPixel(x1, y, z2);
			return;
		}
		z2-=z1;
		z2/=(x2-x1);
		for(;x1<=x2;x1++){
			display.drawPixel(x1, y,z1);
			z1+=z2;
		}
	}
}
class Clipper{
	java.util.List<Float>vertex;
	java.util.List<Integer> lines;
	java.util.List<Integer> tri;
	float x1,y1;
	float x2,y2;
	float z1,z2;
	void Clip(){
		vertex.get(1);
	}
}
