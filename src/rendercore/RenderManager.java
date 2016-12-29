package rendercore;
import eventsystem.ViewPort;
import gcore.Camera;
import gcore.Object;

/**
 * @author sudip
 * Date : Dec 29, 2016
 * -->All other objects or window components will request for rendered output via
 * RenderManager. 
 * This will be the only class available for output from rendercore package. RenderRegistery being for input
 */
public class RenderManager extends Object {
	private static RenderManager defaultManager;
	public static RenderManager getDefaultManager(){
		return defaultManager;
	}
	public void getrenderedOutput(ViewPort requestSender){
		
	}
}
