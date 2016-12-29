package gprimitive;

import gcore.Object3d;
import gcore.Vector4d;
import rendercore.RenderRegistry;
public class Line3d extends Object3d {
	Vector4d vertices[];
	public Line3d(int x1,int y1,int z1,int x2,int y2,int z2) {
		vertices=new Vector4d[2];
		vertices[0].x=x1;
		vertices[0].y=y1;
		vertices[0].z=z1;
		vertices[0].w=1;
		vertices[1].x=x2;
		vertices[1].y=y2;
		vertices[1].z=z2;
		vertices[1].w=1;
		rendercore.RenderRegistry.getDefaultRegistor().addVertexCountHint(2);
		
		// TODO Auto-generated constructor stub
	}
	@Override
	public void draw(){
		RenderRegistry registor=RenderRegistry.getDefaultRegistor();
		registor.registerVertex(vertices);
		registor.registerLine(this);
	}
}
