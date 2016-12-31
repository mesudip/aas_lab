package gcomposite;

import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.generic.NEW;

import gcore.Vector4d;
import gcore.WorldObject;

/**
 * 
 * @author sudip
 * Date : Dec 30, 2016
 * -->
 * Cube class will be for demonstration how a composite object would look like,
 * how it registers itself to the rendering system and how it draws itself.
 * -->Work is currently incomplete
 */
public class Cube extends WorldObject {
	/**
	 * @author sudip
	 * 
	 * Why is this array static and final?
	 * Well i am hoping that each cube in the world can be represented with
	 * same vertex data but different transform component.
	 * If this theory doesn't work, it will be made a instance member not the class member.
	 * 
	 */
	private static final Vector4d[] vertices=new Vector4d[]{
		new Vector4d(1,1,1),
		new Vector4d(1,1,-1),
		new Vector4d(1,-1,1),
		new Vector4d(1,-1,-1),
		new Vector4d(-1,1,1),
		new Vector4d(-1,1,-1),
		new Vector4d(-1,-1,1),
		new Vector4d(-1,-1,-1),
	};
	/**
	 * @author sudip
	 * Date : Dec 80,2016
	 * edge array contains 1-d array. 1st two vertex index represent a edge
	 * and so on..
	 */
	private static final int[] edges=new int[]{
		0,1,0,2,1,3,2,3,
		0,4,1,5,3,7,2,6,
		6,4,4,5,5,7,7,6	};
	public Cube(){
		transform.loadIdentity();
		rendercore.RenderRegistry.getDefaultRegistor().addVertexCountHint(8);
		rendercore.RenderRegistry.getDefaultRegistor().addLineCountHint(12);
	}
	public void Draw(){
		rendercore.RenderRegistry registry=rendercore.RenderRegistry.getDefaultRegistor();
		int start=registry.registerVector(vertices);
		registry.drawLine(start,edges);
		
		
	}

}
