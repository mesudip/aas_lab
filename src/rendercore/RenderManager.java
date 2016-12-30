package rendercore;
import java.sql.Time;
import java.util.Timer;

import eventsystem.ViewPort;
import gcore.Object;
import gcore.Object3d;

/**
 * @author sudip
 * Date : Dec 29, 2016
 * -->All other objects or window components will request for rendered output via
 * RenderManager. 
 * This will be the only class available for output from rendercore package. RenderRegistery being for input
 */
public class RenderManager extends Object {
	private static RenderManager defaultManager;
	private static final PrimitiveRenderer renderer=new PrimitiveRenderer(); 
	int frameNo=0;
	public static RenderManager getDefaultManager(){
		return defaultManager;
	}
	public RenderManager(){
		RenderManager.defaultManager=this;
	}
	public void getrenderedOutput(ViewPort requestSender){
		System.out.println("RenderManager Frame["+String.valueOf(frameNo)+"] : Rendering Started");
		System.out.flush();
		long time=System.currentTimeMillis();
		RenderRegistry.getDefaultRegistor().rebuildArrays();
		//stage 1: Request all objects to register their drawing ways;
		for(Object3d object:Object3d.getObjectlist()){
			object.draw();
			
		}
		//stage2: request for transformations.
		// thest steps will be skipped for this time.
		
		//last stage: draw the objects;
		time=System.currentTimeMillis()-time;
		
		RenderManager.renderer.renderLine(requestSender);
		System.out.println("RenderManager Frame["+String.valueOf(frameNo++)+"] : Render Complete in ["+String.valueOf(time)+"] ms");
		System.out.flush();
			
	}
}
