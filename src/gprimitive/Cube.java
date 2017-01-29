package gprimitive;

import gcore.Object3d;

public class Cube extends Object3d {
	static float[] vertex={
			1,1,1,
			-1,1,1,
			-1,-1,1,
			1,-1,1,
			1,1,-1,
			-1,1,-1,
			-1,-1,-1,
			1,-1,-1
	}; 
	static int[] edge={
			0,1,1,2,2,3,3,0,
			4,5,5,6,6,7,7,4,
			0,4,1,5,6,2,7,3
	};
	static int[]face={
			
	};
	public Cube(){
		
	}
	public void draw() {
		int offset=addVertex3(vertex);
		drawLine(edge, offset);
		// TODO Auto-generated method stub

	}

}
