package gcore;

public class WorldObject extends Object{
	
	public Transform transform;
	protected void applyTransforms(){
		
	}
	public WorldObject(){
		
		transform=new Transform();
		transform.loadIdentity();
	}	
}