package gcore;

import java.util.List;


public class Camera extends gcore.Object {
	static Camera mainCamera=new Camera();
	float far=100,near=20;
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
	public float magnitude(float[] m){
		float ret=(float) Math.sqrt(m[0]*m[0]+m[1]*m[1]+m[2]*m[2]);
		return ret;
	}
	public float[] normalize(float[] m){
		float n=magnitude(m);
		float[] v=new float[3];
		v[0]/=n;
		v[1]/=n;
		v[2]/=n;
		return v;
		
	}
	
	public void p(){
		float[] matrix=projection.getMatrix();
		float[] forward=transform.getRotatedVector(1,1,1);
		//float u=0.1;
		//float v=0.5;
		//forward=normalize(forward);
		float  zvp=-0x00;
		float zprp=-0x5000;
		System.err.println("zvp="+zvp);
		float dp=zprp-zvp;
		float h=(zprp-forward[2])/dp;
		float[] pos=transform.getPosition();
		 matrix[0]=1;
			matrix[1]=0;
			matrix[2]=0;
			matrix[3]=0;
			matrix[4]=0;
			matrix[5]=0;
			matrix[6]=1;
			matrix[7]=0;
			matrix[8]=0;
			matrix[9]=0;
			matrix[10]=-zvp/(dp);
			matrix[11]=zvp*(zprp/(dp));
			matrix[12]=0;
			matrix[13]=0;
			matrix[14]=-1/(dp);
			matrix[15]=zprp/(dp);
			
			//projection.multiplyBefore(matrix);
			/*matrix[0]/=h;
			matrix[1]/=h;
			matrix[2]/=h;
			matrix[3]/=h;
			matrix[4]/=h;
			matrix[5]/=h;
			matrix[6]/=h;
			matrix[7]/=h;
			matrix[8]/=h;
			matrix[9]/=h;
			matrix[10]/=h;
			matrix[11]/=h;
			matrix[12]/=h;
			matrix[13]/=h;
			matrix[14]/=h;
			matrix[15]/=h;*/
			

		/*transform.setEulerAngles(30, 30,30);
		 * 
		matrix[0]=1;
		matrix[2]=-(forward[0]/forward[2]);
		matrix[5]=1;
		matrix[6]=-(forward[1]/forward[2]);
		matrix[10]=1;
		matrix[14]=1/forward[2];
		matrix[1]=matrix[1]=matrix[3]=matrix[4]=matrix[7]=matrix[8]=matrix[9]=matrix[11]=matrix[12]=matrix[13]=matrix[15]=0;
		 matrix[0]*=0.5/forward[2];
		 matrix[5]*=0.5/forward[2];
		 matrix[10]*=0.5/forward[2];
		 matrix[15]=1;*/
		
		/*float width=500;
		
		float height=400;
		//float ratio=(float)(width/height);
		float ang = 120; // some view angle
	    float ratio = (float)width/height; // ratio of the screen
	    float maxDepth = 0x5000;
	    float tangent = (float)Math.tan((Math.PI/180)*(ang/2) );
	    float[] position=projection.getPosition();
	    matrix[0]=projection.x;
		matrix[1]=0;
		matrix[2]=0;
		matrix[3]=0;
		matrix[4]=0;
		matrix[5]=(float)(ratio/tangent);
		matrix[6]=0;
		matrix[7]=0;
		matrix[8]=0;
		matrix[9]=0;
		matrix[10]=(float)((far+near)/(near-far));
		matrix[11]=-(float)(2*far*near/(far-near));
		matrix[12]=0;
		matrix[13]=0;
		matrix[14]=-1;
		matrix[15]=0;
		projection.multiplyBefore(matrix);
	    matrix[0]=1/tangent;
		matrix[1]=0;
		matrix[2]=0;
		matrix[3]=0;
		matrix[4]=0;
		matrix[5]=(float)(ratio/tangent);
		matrix[6]=0;
		matrix[7]=0;
		matrix[8]=0;
		matrix[9]=0;
		matrix[10]=(float)((far+near)/(near-far));
		matrix[11]=-(float)(2*far*near/(far-near));
		matrix[12]=0;
		matrix[13]=0;
		matrix[14]=-1;
		matrix[15]=0;
		projection.multiplyBefore(matrix);
		 matrix[0]=width;
			matrix[1]=0;
			matrix[2]=0;
			matrix[3]=width/2;
			matrix[4]=0;
			matrix[5]=-height;
			matrix[6]=0;
			matrix[7]=height/2;
			matrix[8]=0;
			matrix[9]=0;
			matrix[10]=-(float)(0.5*maxDepth);
			matrix[11]=(float)(0.5*maxDepth);
			matrix[12]=0;
			matrix[13]=0;
			matrix[14]=0;
			matrix[15]=1;*/
	}
	
	
	public void setRotationDepth(float depth){
		rotationDepth=depth;
	}
	
	public void setOrthoProjection(double left,double right,double bottom,double top,double near,double far){
    /*
		float[] matrix=projection.getMatrix();
		matrix[0]=(float)(2*near/(right-left));
		matrix[1]=0;
		matrix[2]=(float)((left + right)/(left - right));
		matrix[3]=0;
		matrix[4]=0;
		matrix[5]=(float)(2*near/(top-bottom));
		matrix[6]=(float)((bottom + top)/(bottom - top));
		matrix[7]=0;
		matrix[8]=0;
		matrix[9]=0;
		matrix[10]=(float)((far+near)/(near-far));
		matrix[11]=(float)(2*far*near/(far-near));
		matrix[12]=0;
		matrix[13]=0;
		matrix[14]=0;
		matrix[15]=0;*/
	/*	float[] matrix=projection.getMatrix();
		float[] forward=transform.getRotatedVector(0, 0, -1);
		float theta=(float) Math.PI/180;
		float d1,d2,d3;
		d1= (float)(Math.cos(theta*45)*(Math.sin(theta*45)*forward[1]+ Math.cos(theta*45)*forward[0])-Math.sin(theta*45)*forward[2]);
		*/
		 
	}
	public void setOrthoProjection(double width,double height){
	      transform.translate((float)(height/2), (float)(width/2),(float)0);
	}
	public void setPerspective(){
		frustumf2((float)-0.1,(float) 0.3,(float) -0.1,(float) 0.3,(float) 0.2, 1);
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
		Transform p=new Transform(projection);
		
		t.translate(Object3d.viewPortWidth/(float)2, Object3d.viewPortHeight/(float)2,0);
		//p.translate(Object3d.viewPortWidth/(float)2, Object3d.viewPortHeight/(float)2,0);

		t.revert();
		t.print();
		
		t.apply(projection);
		t.applyOn(vertices);
		//p.applyOn(vertices);
	    /*float[] matrix= projection.getMatrix();
	    matrix[15]=1;
	    p.applyOn(vertices);*/
	}
	public void freeMouseRotation(int dx,int dy) {
		float[] rotated=transform.getRotatedVector(0,0, 1);
		transform.rotate(rotated[0], rotated[1], rotated[2], 1);
		// TODO Auto-generated method stub
		
	}
}
