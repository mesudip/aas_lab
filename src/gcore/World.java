package gcore;

import java.util.ArrayList;
import rendercore.RenderManager;
import rendercore.RenderRegistry;
/**
 * 
 * @author sudip
 * Date : Jan 2, 2017
 * -->
 * Of course, everything that exists, exists in a world. A world
 * needs to be created first in order to create anything in the game.
 * Unlike in reality, One can create different worlds. Objects in one world
 * is in no way connected to object of another way. Different worlds will
 * have their own list of objects.
 */

public class World extends Object {
	private ArrayList<gcore.Object> objectList=new ArrayList<>();
	private rendercore.RenderManager manager =new RenderManager();
	private rendercore.RenderRegistry register=new RenderRegistry();
	public static final void setActiveWorld(World world){
		currentWorld=world;
	}
	public World(){
		currentWorld=this;
	}
	public final ArrayList<gcore.Object> getObjects(){
		return objectList;
	}
	public final rendercore.RenderManager getManager(){
		return manager;
	}
	public final rendercore.RenderRegistry getRegistry(){
		return register;
	}
	public final gcore.Object getObjectById(){
		return new Object();
	}

}
