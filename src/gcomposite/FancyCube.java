package gcomposite;



import gprimitive.Cube;

public class FancyCube extends Cube {
	public FancyCube(){
		color=new int[]{
				0xffff0000,0xffff0000,//red
				0xff00ff00,0xff00ff00,//green
				0xff0000ff,0xff0000ff,//blue
				0xffffff00,0xffffff00,//orange
				0xffff00ff,0xffff00ff,//red+blue
				0xff00ffff,0xff00ffff//green+blue
		};
	}
	@Override
	public void draw(){
		int offset=addVertex3(vertex);
		drawTriangle(tri, color,offset);
		//drawLine(edge, offset);
	}
}
