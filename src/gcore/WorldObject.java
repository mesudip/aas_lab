package gcore;

public class WorldObject extends Object {
	Vector4d position;
	Vector4d rotation;
	Vector3d scale;
	
	public WorldObject(){
		position.x=0;
		position.y=0;
		position.z=0;
		position.w=1;
		
		rotation.x=0;
		rotation.y=0;
		rotation.z=0;
		rotation.w=0;
		
		scale.x=1;
		scale.y=1;
		scale.z=1;
	}
	
	public void setPosition(float x, float y, float z){
		
	}
	public void setRotation(float x, float y, float z){
		
	}
	public void setScale(float x, float y, float z){
	
	}
}
