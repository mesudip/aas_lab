package gprimitive;

import gcore.Object3d;

public class Cube extends Object3d {
	protected static float[] vertex={
			1,1,1,
			-1,1,1,
			-1,-1,1,
			1,-1,1,
			1,1,-1,
			-1,1,-1,
			-1,-1,-1,
			1,-1,-1
	}; 
	protected static int[] edge={
			0,1,1,2,2,3,3,0,
			4,5,5,6,6,7,7,4,
			0,4,1,5,6,2,7,3
	};
	protected static int[]tri={
			0,1,3,//front
			3,1,2,
			4,0,3,//right
			4,3,7,
			5,4,7,//back
			5,7,6,
			1,5,6,//left
			2,1,6,
			5,1,4,//top
			1,0,4,
			6,7,2,//bottom
			2,7,3
	};
	protected int[] color=null;
	int cubeColor=0xff000000;
	public Cube(){
		
	}
	public void draw() {
		activeColor=cubeColor;
		int offset=addVertex3(vertex);
		drawLine(edge, offset);
		// TODO Auto-generated method stub

	}
	@Override
	public void setColor(int color) {
		cubeColor=color;
	}

}
