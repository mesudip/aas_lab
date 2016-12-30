package gcore;

public class WorldObject extends Object {
	public Transform transform;
	
	public WorldObject(){
		super();
		transform=new Transform();
		transform.loadIdentity();
	}
	
}
