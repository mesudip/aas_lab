package gcore;
import java.lang.Math;
import java.util.List;


public class Camera extends gcore.Object {
	static Camera mainCamera=new Camera();
	float far=-100,near=20;
	public Transform transform=new Transform();
	public Transform projection=new Transform();
	float ua,ub,uc;//The direction ratios of the up direction;
	float rotationDepth=500;
	static final float zoomscale=(float) 0.1;
	private Camera(){
		transform.loadIdentity();
		projection.loadIdentity();
	}
	public void lookAt(Object3d object){
		
	}
	public static Camera getCamera(){
		return mainCamera;
	}
	public void translate(float x,float y,float z){
		//transform.translate(-x,-y,-z);
		transform.translate(x,y,z);
	}
	public Transform getTransform(){
		return transform;
	}
	public Transform getProjection(){
		return projection;
	}
	public void zoom(int value){
		float [] scale=transform.getScale();
		float[] pos=transform.getPosition();
		transform.translate(-pos[0],-pos[1],-pos[2]);
		if(value<0){
			transform.scale((float)1.1,(float)1.1,(float)1.1);
		}
		else{
			transform.scale((float)0.909090909,(float)0.909090909,(float)0.909090909);
		}
		transform.translate(pos[0],pos[1],pos[2]);
		System.out.println("Camera scale :"+scale[0]+", "+scale[1]+", "+scale[2]);
	}
	public void rotateOnDrag(int dx,int dy){
		//Horizontal Drag only detected now
		float[] forward=transform.getRotatedVector(0, 0, -1);
		float[] up=transform.getRotatedVector(0, 1, 0);
		float[] pos=transform.getPosition();
		forward[0]=forward[0]*100+pos[0];
		forward[1]=forward[1]*100+pos[1];
		forward[2]=forward[2]*100+pos[2];
		if(dx!=0)
		transform.rotate(up[0], up[1], up[2], pos[0],pos[1],pos[2],((double)2)*dx/Math.abs(dx));
		System.out.println(" Current forward vector is:("+forward[0]+", "+forward[1]+", "+forward[2]);
	}
	public void rotatexOnDrag(int dx,int dy){
		//Horizontal Drag only detected now
		float[] forward=transform.getRotatedVector(0, 0, -1);
		float[] right=transform.getRotatedVector(1, 0, 0);
		float[] pos=transform.getPosition();
		//pos[0]=forward[0]*100+pos[0];
		//pos[1]=forward[1]*100+pos[1];
		//pos[2]=forward[2]*100+pos[2];
		if(dx!=0)
		transform.rotate(right[0], right[1], right[2], pos[0],pos[1],pos[2],((double)2)*dx/Math.abs(dx));
		System.out.println(" Current forward vector is:("+forward[0]+", "+forward[1]+", "+forward[2]);
	}
	public void rotateyOnDrag(int dx,int dy){
		//Horizontal Drag only detected now
		float[] forward=transform.getRotatedVector(0, 0, -1);
		float[] up=transform.getRotatedVector(0, 1, 0);
		float[] pos=transform.getPosition();
		forward[0]=forward[0]*100+pos[0];
		forward[1]=forward[1]*100+pos[1];
		forward[2]=forward[2]*100+pos[2];
		if(dx!=0)
		transform.rotate(up[0], up[1], up[2], pos[0],pos[1],pos[2],((double)2)*dx/Math.abs(dx));
		System.out.println(" Current forward vector is:("+forward[0]+", "+forward[1]+", "+forward[2]);
	}
	public void rotatezOnDrag(int dx,int dy){
		//Horizontal Drag only detected now
		float[] forward=transform.getRotatedVector(0, 0, -1);
		float[] up=transform.getRotatedVector(0, 1, 0);
		float[] pos=transform.getPosition();
		pos[0]=forward[0]*100+pos[0];
		pos[1]=forward[1]*100+pos[1];
		pos[2]=forward[2]*100+pos[2];
		if(dx!=0)
		transform.rotate(forward[0], forward[1], forward[2],pos[0],pos[1],pos[2],((double)2)*dx/Math.abs(dx));
		System.out.println(" Current forward vector is:("+forward[0]+", "+forward[1]+", "+forward[2]);
		System.out.println(" Current position       is:("+pos[0]+", "+pos[1]+", "+pos[2]);
	}
	
	public void setRotationDepth(float depth){
		rotationDepth=depth;
	}
	public void setOrthoProjection(double left,double right,double bottom,double top,double near,double far){

		float[] matrix=transform.getMatrix();
		matrix[0]=(float)(2*near/(right-left));
		matrix[1]=0;
		matrix[2]=-(float)((left + right)/(left - right));
		matrix[3]=0;
		matrix[4]=0;
		matrix[5]=-(float)(2*near/(top-bottom));
		matrix[6]=-(float)((bottom + top)/(bottom - top));
		matrix[7]=-(float)((top+bottom)/2);
		matrix[8]=0;
		matrix[9]=0;
		matrix[10]=-(float)((far+near)/(near-far));
		matrix[11]=-(float)(2*far*near/(far-near));
		matrix[12]=0;
		matrix[13]=0;
		matrix[14]=1;
		matrix[15]=1;
	}
	public void setOrthoProjection(double width,double height){
		transform.translate((float)(height/2), (float)(width/2),(float)0);
	}
	public void setPerspective(){
		setOrthoProjection((float)-100,(float) 100,(float) -100,(float) 100,(float) 0, 100);
	}
	
	public void frustumf2(float left, float right, float bottom, float top,float znear, float zfar)
	{
		float[] matrix=projection.getMatrix();
		float temp, temp2, temp3, temp4;
		temp = 2 * znear;
		temp2 = right - left;
		temp3 = top - bottom;
		temp4 = zfar - znear;
		matrix[0] = temp / temp2;
		matrix[5] = temp / temp3;
		matrix[8] = (right + left) / temp2;
		matrix[9] = (top + bottom) / temp3;
		matrix[10] = (-zfar - znear) / temp4;
		matrix[11] = -1;
		matrix[14] = (-temp * zfar) / temp4;
		matrix[15] = 1;
	}
	public void applyTransforms(List<Float>vertices){
		Transform t=new Transform(transform);
		t.translate(Object3d.viewPortWidth/(float)2, Object3d.viewPortHeight/(float)2,0);
		t.revert();
		t.apply(projection);	
		t.applyOn(vertices);
	}
	public void freeMouseRotation(int dx,int dy) {
		float[] rotated=transform.getRotatedVector(0,0, 1);
		transform.rotate(rotated[0], rotated[1], rotated[2], 1);
		// TODO Auto-generated method stub
		
	}
	
	public float[] getFrontVector(){
		return transform.getRotatedVector(0, 0, -1);
	}
	
	public float[] getUpVector(){
		return transform.getRotatedVector(0, 1, 0);
	}
}
