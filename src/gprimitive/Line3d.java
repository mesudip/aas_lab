package gprimitive;

import gcore.Object3d;
import gcore.Vector4d;
import rendercore.RenderRegistry;

/**
 * @author sudip
 * Date : Dec 30, 2016
 * --> I think we should eliminate the need of having the 
 * transform object in Line3d. This will just take space.
 * And Line is supposed to be a simple primitive type.
 * So it should be small as possible and fast.
 */
public class Line3d extends Object3d {
	Vector4d vertices[];
	public Line3d(int x1,int y1,int z1,int x2,int y2,int z2) {
		
		this.vertices=new Vector4d[2];
		vertices[0]=new Vector4d(x1,y1,z1,1);
		vertices[1]=new Vector4d(x2,y2,z2,1);
		rendercore.RenderRegistry.getDefaultRegistor().addVertexCountHint(2);
		
		// TODO Auto-generated constructor stub
	}
	@Override
	public void draw(){
		RenderRegistry registor=RenderRegistry.getDefaultRegistor();
		int vertexStart=registor.registerVector(vertices);
		registor.drawLine(vertexStart,vertexStart+1);
	}
}
