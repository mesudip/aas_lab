package rendercore;
import eventsystem.ViewPort;
import gcore.Camera;
import gcore.Drawable;
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
	public static RenderManager getDefaultManager(){
		return defaultManager;
	}
	public void getrenderedOutput(ViewPort requestSender){
		RenderRegistry.getDefaultRegistor().rebuildArrays();
		//stage 1: Request all objects to register their drawing ways;
		for(Drawable object:Object3d.getObjectlist()){
			object.draw();
		}
		//stage2: request for transformations.
		// thest steps will be skipped for this time.
		
		//last stage: draw the objects;
		//renderer.renderLine(RenderRegistry.getDefaultRegistor().);
			
	}
}
