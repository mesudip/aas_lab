package gcore;

public class WorldObject extends Object {
	Transform transform;
	
	public WorldObject(){
		super();
		transform=new Transform();
		transform.loadIdentity();
	}
}
