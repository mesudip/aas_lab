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
			/**
			 * @author sudip
			 * Well bersenham's line drawing is aplied here.
			 */
			
			x1=(int)vertices[lines[i]].x;
			y1=(int)vertices[lines[i]].y;
			i++;
			x2=(int)vertices[lines[i]].x;
			y2=(int)vertices[lines[i]].y;
			dx=x2-x1;
			dy=y2-y1;
			if(dx<0) /* is was wondering whether there is a way te remove if sentence and get the same result by some calculation.*/
				xinc=-1;
			if(dy<0)
				yinc=-1;
			dy=Math.abs(dy);
			dx=Math.abs(dx);
			if(dx>dy){
				p=2*dy-dx;
				while(x1!=x2){
					port.drawPixel(x1, y1);
					x1+=xinc;
					if(p<0){
						p+=2*dy;
					}
					else{
						y1+=yinc;
						p+=2*dy-2*dx;
					}
				}
			}
			else{
				p=2*dx-dy;
				while(y1!=y2){
					port.drawPixel(x1, y1);
					y1+=yinc;
					if(p<0){
						p+=2*dx;
					}
					else{
						x1+=xinc;
						p+=2*dx-2*dy;
					}
				}
				
			}
		}
	}
	public void renderTriangle(){}
	public void renderQuad(){}
	public void convexPoly(){}
}
	