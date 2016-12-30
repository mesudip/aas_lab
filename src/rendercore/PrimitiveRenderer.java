package rendercore;
import eventsystem.ViewPort;
import gcore.Object;
import gcore.Vector4d;


public class PrimitiveRenderer extends Object {
	public void renderPoint(){}
	public void renderLine(ViewPort port){
		Vector4d [] vertices=RenderRegistry.getDefaultRegistor().getVertex().toArray(new Vector4d[0]);
		Integer []  lines=(Integer[])RenderRegistry.getDefaultRegistor().getLines().toArray(new Integer[0]);
		//Vector4d start,end;
		int x1,y1,x2,y2;
		int dx,dy;
		int xinc=1,yinc=1;
		int p;
		for( int i=0;i<lines.length;i++){
			System.err.print(" PrimitiveRenderer Line["+String.valueOf(i/2)+"] : Starting");
			
			/**
			 * @author sudip
			 * Well bersenham's line drawing is aplied here.
			 */
			x1=(int)vertices[lines[i]].x;
			y1=(int)vertices[lines[i]].y;
			i++;
			x2=(int)vertices[lines[i]].x;
			y2=(int)vertices[lines[i]].y;
			System.err.println("("+String.valueOf(x1)+", "+String.valueOf(y1)+") to ("+String.valueOf(x2)+", "+String.valueOf(y2)+")");
			System.err.flush();
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
			port.drawPixel(x1, y1);
			if(Math.abs(dx)>Math.abs(dy)){
				p=2*dy-dx;
				while(x1!=x2){
					x1+=xinc;
					if(p>=0)	{
						y1+=yinc;
						p-=2*dx;
					}
					p+=2*dy;
					port.drawPixel(x1, y1);
				}
			}
			else{
				p=2*dx-dy;
				while(y1!=y2){
					y1+=yinc;
					if(p>=0){
						x1+=xinc;
						p-=2*dy;
					}
					p+=2*dx;
					port.drawPixel(x1, y1);
				}
			}
			System.err.println(" PrimitiveRenderer Line["+String.valueOf(i/2)+"] : Drawing complete");
			System.err.flush();
		}
		
	}
	public void renderTriangle(){}
	public void renderQuad(){}
	public void convexPoly(){}
}
	