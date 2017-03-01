package gcore;
import java.nio.file.attribute.PosixFileAttributes;
import java.util.List;

import javafx.scene.SceneAntialiasing;

public class Transform {
	//this is the position of the object;
	float x,y,z;
	float[] matrix=new float[16];
	public Transform(){	
	}
	public Transform(Transform t){
		x=t.x;
		y=t.y;
		z=t.y;
		for(int i=0;i<15;i++){
			matrix[i]=t.matrix[i];
		}
	}
	public void translate(float x, float y){
		matrix[0]+=matrix[12]*x;
		matrix[1]+=matrix[13]*x;
		matrix[2]+=matrix[14]*x;
		matrix[3]+=matrix[15]*x;
		
		matrix[4]+=matrix[12]*y;
		matrix[5]+=matrix[13]*y;
		matrix[6]+=matrix[14]*y;
		matrix[7]+=matrix[15]*y;
	}
	public void translate(float x,float y,float z){
		matrix[0]+=matrix[12]*x;
		matrix[1]+=matrix[13]*x;
		matrix[2]+=matrix[14]*x;
		matrix[3]+=matrix[15]*x;
		
		matrix[4]+=matrix[12]*y;
		matrix[5]+=matrix[13]*y;
		matrix[6]+=matrix[14]*y;
		matrix[7]+=matrix[15]*y;
		
		matrix[8]+=matrix[12]*z;
		matrix[9]+=matrix[13]*z;
		matrix[10]+=matrix[14]*z;
		matrix[11]+=matrix[15]*z;
		
	}
	public void loadIdentity(){
		matrix[0]=1;  matrix[1]=0;  matrix[2]=0;   matrix[3]=0;
		matrix[4]=0;  matrix[5]=1;  matrix[6]=0;   matrix[7]=0;
		matrix[8]=0;  matrix[9]=0;  matrix[10]=1;  matrix[11]=0;
		matrix[12]=0; matrix[13]=0; matrix[14]=0;  matrix[15]=1;
	}
	public void applyOn(List<Float> vertices)
	{
		float x,y,z,w;
		for(int i=0;i<vertices.size();){
			x=vertices.get(i);
			y=vertices.get(i+1);
			z=vertices.get(i+2);
			w=vertices.get(i+3);
			vertices.set(i++,x*matrix[0]+y*matrix[1]+z*matrix[2]+w*matrix[3]);
			vertices.set(i++,x*matrix[4]+y*matrix[5]+z*matrix[6]+w*matrix[7]);
			vertices.set(i++,x*matrix[8]+y*matrix[9]+z*matrix[10]+w*matrix[11]);
			vertices.set(i++,x*matrix[12]+y*matrix[13]+z*matrix[14]+w*matrix[15]);
		}
	}
	public void stdRotatex(double angle){
		angle*=Math.PI;
		angle/=180;
		float cos_t=(float)Math.cos(angle);
		float sin_t=(float)Math.sin(angle);
		float tmp=matrix[4]*sin_t;
		matrix[4]*=cos_t;
		matrix[4]-=matrix[8]*sin_t;
		matrix[8]*=cos_t;
		matrix[8]+=tmp;
		
		tmp=matrix[5]*sin_t;
		matrix[5]*=cos_t;
		matrix[5]-=matrix[9]*sin_t;
		matrix[9]*=cos_t;
		matrix[9]+=tmp;
		
		tmp=matrix[6]*sin_t;
		matrix[6]*=cos_t;
		matrix[6]-=matrix[10]*sin_t;
		matrix[10]*=cos_t;
		matrix[10]+=tmp;
		
		tmp=matrix[7]*sin_t;
		matrix[7]*=cos_t;
		matrix[7]-=matrix[11]*sin_t;
		matrix[11]*=cos_t;
		matrix[11]+=tmp;
		
	}
	public void stdRotatez(double angle){
		angle*=Math.PI;
		angle/=180;
		float cos_t=(float)Math.cos(angle);
		float sin_t=(float)Math.sin(angle);
		
		float tmp=matrix[0]*sin_t;
		matrix[0]*=cos_t;
		matrix[0]-=matrix[4]*sin_t;
		matrix[4]*=cos_t;
		matrix[4]+=tmp;
		
		tmp=matrix[1]*sin_t;
		matrix[1]*=cos_t;
		matrix[1]-=matrix[5]*sin_t;
		matrix[5]*=cos_t;
		matrix[5]+=tmp;
		
		tmp=matrix[2]*sin_t;
		matrix[2]*=cos_t;
		matrix[2]-=matrix[6]*sin_t;
		matrix[6]*=cos_t;
		matrix[6]+=tmp;
		
		tmp=matrix[3]*sin_t;
		matrix[3]*=cos_t;
		matrix[3]-=matrix[7]*sin_t;
		matrix[7]*=cos_t;
		matrix[7]+=tmp;
	}
	public void stdRotatey(double angle){
		angle*=Math.PI;
		angle/=180;
		float cos_t=(float)Math.cos(angle);
		float sin_t=(float)Math.sin(angle);
		
		float tmp=matrix[0]*sin_t;
		matrix[0]*=cos_t;
		matrix[0]+=matrix[8]*sin_t;
		matrix[8]*=cos_t;
		matrix[8]-=tmp;
		
		tmp=matrix[1]*sin_t;
		matrix[1]*=cos_t;
		matrix[1]+=matrix[9]*sin_t;
		matrix[9]*=cos_t;
		matrix[9]-=tmp;
		
		tmp=matrix[2]*sin_t;
		matrix[2]*=cos_t;
		matrix[2]+=matrix[10]*sin_t;
		matrix[10]*=cos_t;
		matrix[10]-=tmp;
		
		tmp=matrix[3]*sin_t;
		matrix[3]*=cos_t;
		matrix[3]+=matrix[11]*sin_t;
		matrix[11]*=cos_t;
		matrix[11]-=tmp;
	}
	public void rotatex(double angle){
		angle*=Math.PI;
		angle/=180;
		float cos_t=(float)Math.cos(angle);
		float sin_t=(float)Math.sin(angle);
		float[] pos=getPosition();
		float k1=cos_t-1;
		float k2=k1*pos[2]+pos[1]*sin_t;
		k1*=pos[1];k1-=pos[2]*sin_t;
		
		float tmp=matrix[4]*sin_t;
		matrix[4]*=cos_t;
		matrix[4]-=matrix[8]*sin_t+k1*matrix[12];
		matrix[8]*=cos_t;
		matrix[8]+=tmp+k2*matrix[12];
		
		tmp=matrix[5]*sin_t;
		matrix[5]*=cos_t;
		matrix[5]-=matrix[9]*sin_t+k1*matrix[13];
		matrix[9]*=cos_t;
		matrix[9]+=tmp+k2*matrix[13];
		
		tmp=matrix[6]*sin_t;
		matrix[6]*=cos_t;
		matrix[6]-=matrix[10]*sin_t+k1*matrix[14];
		matrix[10]*=cos_t;
		matrix[10]+=tmp+k2*matrix[14];
		
		tmp=matrix[7]*sin_t;
		matrix[7]*=cos_t;
		matrix[7]-=matrix[11]*sin_t+k1*matrix[15];
		matrix[11]*=cos_t;
		matrix[11]+=tmp+k2*matrix[15];
	}
	public void rotatey(double angle){
		angle*=Math.PI;
		angle/=180;
		float cos_t=(float)Math.cos(angle);
		float sin_t=(float)Math.sin(angle);
		
		float[] pos=getPosition();
		float k1=cos_t-1;
		float k2=k1*pos[2]-pos[0]*sin_t;
		k1*=pos[0];k1+=pos[2]*sin_t;
		
		float tmp=matrix[0]*sin_t;
		matrix[0]*=cos_t;
		matrix[0]+=matrix[8]*sin_t+k1*matrix[12];
		matrix[8]*=cos_t;
		matrix[8]-=tmp+k2*matrix[12];
		
		tmp=matrix[1]*sin_t;
		matrix[1]*=cos_t;
		matrix[1]+=matrix[9]*sin_t+k1*matrix[13];
		matrix[9]*=cos_t;
		matrix[9]-=tmp+k2*matrix[13];
		
		tmp=matrix[2]*sin_t;
		matrix[2]*=cos_t;
		matrix[2]+=matrix[10]*sin_t+k1*matrix[14];
		matrix[10]*=cos_t;
		matrix[10]-=tmp+k2*matrix[14];
		
		tmp=matrix[3]*sin_t;
		matrix[3]*=cos_t;
		matrix[3]+=matrix[11]*sin_t+k1*matrix[15];
		matrix[11]*=cos_t;
		matrix[11]-=tmp+k2*matrix[15];
	}
	public void rotatez(double angle){
		angle*=Math.PI;
		angle/=180;
		float cos_t=(float)Math.cos(angle);
		float sin_t=(float)Math.sin(angle);
		float[] pos=getPosition();
		float k1=cos_t-1;
		float k2=k1*pos[1]+pos[0]*sin_t;
		k1*=pos[0];k1-=pos[1]*sin_t;
		
		float tmp=matrix[0]*sin_t;
		matrix[0]*=cos_t;
		matrix[0]-=matrix[4]*sin_t+k1*matrix[12];
		matrix[4]*=cos_t;
		matrix[4]+=tmp+k2*matrix[12];
		
		tmp=matrix[1]*sin_t;
		matrix[1]*=cos_t;
		matrix[1]-=matrix[5]*sin_t+k1*matrix[13];
		matrix[5]*=cos_t;
		matrix[5]+=tmp+k2*matrix[13];
		
		tmp=matrix[2]*sin_t;
		matrix[2]*=cos_t;
		matrix[2]-=matrix[6]*sin_t+k1*matrix[14];
		matrix[6]*=cos_t;
		matrix[6]+=tmp+k2*matrix[14];
		
		tmp=matrix[3]*sin_t;
		matrix[3]*=cos_t;
		matrix[3]-=matrix[7]*sin_t+k1*matrix[15];
		matrix[7]*=cos_t;
		matrix[7]+=tmp+k2*matrix[15];
	}
	/**
	 * 
	 * @param a 
	 * @param b
	 * @param c
	 * @param angle
	 * the direction cosines l,m,n can be used instead of a,b,c but the effect is same.
	 */
	public void rotate(double u,double v,double w,double angle){
		angle*=Math.PI;
		angle/=180;
		double r=Math.sqrt(u*u+v*v+w*w);
		u=u/r;v=v/r;w=w/r;
		double uSq=u*u,vSq=v*v,wSq=w*w;
		double sin_t=Math.sin(angle);
		double cos_t=Math.cos(angle);
		double oneMinusCos_t=1-cos_t;
		float[] matrix=new float[16];
		float[] position=getPosition();
		matrix[0]=(float)(uSq+(vSq+wSq)*cos_t);
		matrix[1]=(float)(u*v*oneMinusCos_t-w*sin_t);
		matrix[2]=(float)(u*w*oneMinusCos_t+v*sin_t);
		matrix[3]=(float)((position[0]*(vSq+wSq)-u*(position[1]*v+position[2]*w))*oneMinusCos_t+(position[1]*w-position[2]*v)*sin_t);
		
		matrix[4]=(float)(u*v*oneMinusCos_t+w*sin_t);
		matrix[5]=(float)(vSq+(uSq+wSq)*cos_t);
		matrix[6]=(float)(v*w*oneMinusCos_t-u*sin_t);
		matrix[7]=(float)((position[1]*(uSq+wSq)-v*(position[0]*u+position[2]*w))*oneMinusCos_t+(position[2]*u-position[0]*w)*sin_t);
		
		matrix[8]=(float)(u*w*oneMinusCos_t-v*sin_t);
		matrix[9]=(float)(v*w*oneMinusCos_t+u*sin_t);
		matrix[10]=(float)(wSq+(uSq+vSq)*cos_t);
		matrix[11]=(float)((position[2]*(uSq+vSq)-w*(position[0]*u+position[1]*v))*oneMinusCos_t+(position[0]*v-position[1]*u)*sin_t);
		
		matrix[12]=matrix[13]=matrix[14]=0;
		matrix[15]=1;
		
		multiplyBefore(matrix);
		
	}
	public void rotate(double u,double v,double w,double x,double y,double z,double angle){
		angle*=Math.PI;
		angle/=180;
		double r=Math.sqrt(u*u+v*v+w*w);
		u=u/r;v=v/r;w=w/r;
		double uSq=u*u,vSq=v*v,wSq=w*w;
		double sin_t=Math.sin(angle);
		double cos_t=Math.cos(angle);
		double oneMinusCos_t=1-cos_t;
		float[] matrix=new float[16];
		
		matrix[0]=(float)(uSq+(vSq+wSq)*cos_t);
		matrix[1]=(float)(u*v*oneMinusCos_t-w*sin_t);
		matrix[2]=(float)(u*w*oneMinusCos_t+v*sin_t);
		matrix[3]=(float)((x*(vSq+wSq)-u*(y*v+z*w))*oneMinusCos_t+(y*w-z*v)*sin_t);
		
		matrix[4]=(float)(u*v*oneMinusCos_t+w*sin_t);
		matrix[5]=(float)(vSq+(uSq+wSq)*cos_t);
		matrix[6]=(float)(v*w*oneMinusCos_t-u*sin_t);
		matrix[7]=(float)((y*(uSq+wSq)-v*(x*u+z*w))*oneMinusCos_t+(z*u-x*w)*sin_t);
		
		matrix[8]=(float)(u*w*oneMinusCos_t-v*sin_t);
		matrix[9]=(float)(v*w*oneMinusCos_t+u*sin_t);
		matrix[10]=(float)(wSq+(uSq+vSq)*cos_t);
		matrix[11]=(float)((z*(uSq+vSq)-w*(x*u+y*v))*oneMinusCos_t+(x*v-y*u)*sin_t);
		matrix[12]=matrix[13]=matrix[14]=0;
		matrix[15]=1;		
		multiplyBefore(matrix);
	}
	public void setRotation(double a,double b,double c){
		double r_bc=Math.sqrt(b*b+c*c);
		double r_ac=Math.sqrt(a*a+c*c);
		
		float	cos_a=(float)(c/r_bc),
				sin_a=(float)(b/r_bc),
				cos_t=(float)(c/r_ac),
				sin_t=-(float)(a/r_ac);
		
		matrix[0]=cos_t;		matrix[1]=0;			matrix[2]=-sin_t;
		matrix[4]=-sin_t*sin_a;	matrix[5]=cos_a;		matrix[6]=-sin_a*cos_t;
		matrix[8]=cos_a*sin_t;	matrix[9]=sin_a;		matrix[10]=cos_a*cos_t;	
		matrix[12]=0;			matrix[13]=0;			matrix[14]=0;				matrix[15]=1;
	}
	public void setEulerAngles(double phi,double theta,double psi){
	}
	public void moveTo(float x,float y,float z){
		float[] pos=getPosition();
		translate(x-pos[0], y-pos[1],z-pos[2]);
	}
	public void scale (float x){
		scale(x,x,x);
	}
	public void scale(float x,float y,float z){
		matrix[0]*=x;
		matrix[1]*=x;
		matrix[2]*=x;
		matrix[3]*=x;
		
		matrix[4]*=y;
		matrix[5]*=y;
		matrix[6]*=y;
		matrix[7]*=y;
		
		matrix[8]*=z;
		matrix[9]*=z;
		matrix[10]*=z;
		matrix[11]*=z;
	}
	public void print(){
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				System.out.printf("%.2f",matrix[i*4+j]);
				System.out.print('\t');
			}
			System.out.println();
		}
	}
	public float[] getMatrix(){
		return matrix;
	}
	protected void multiplyBefore(float[] m2){
		float[] ans=new float[16];
		ans[0]=m2[0]*matrix[0]+m2[1]*matrix[4]+m2[2]*matrix[8]+m2[3]*matrix[12];
		ans[1]=m2[0]*matrix[1]+m2[1]*matrix[5]+m2[2]*matrix[9]+m2[3]*matrix[13];
		ans[2]=m2[0]*matrix[2]+m2[1]*matrix[6]+m2[2]*matrix[10]+m2[3]*matrix[14];
		ans[3]=m2[0]*matrix[3]+m2[1]*matrix[7]+m2[2]*matrix[11]+m2[3]*matrix[15];
		
		ans[4]=m2[4]*matrix[0]+m2[5]*matrix[4]+m2[6]*matrix[8]+m2[7]*matrix[12];
		ans[5]=m2[4]*matrix[1]+m2[5]*matrix[5]+m2[6]*matrix[9]+m2[7]*matrix[13];
		ans[6]=m2[4]*matrix[2]+m2[5]*matrix[6]+m2[6]*matrix[10]+m2[7]*matrix[14];
		ans[7]=m2[4]*matrix[3]+m2[5]*matrix[7]+m2[6]*matrix[11]+m2[7]*matrix[15];
		
		ans[8] =m2[8]*matrix[0]+m2[9]*matrix[4]+m2[10]*matrix[8]+m2[11]*matrix[12];
		ans[9] =m2[8]*matrix[1]+m2[9]*matrix[5]+m2[10]*matrix[9]+m2[11]*matrix[13];
		ans[10]=m2[8]*matrix[2]+m2[9]*matrix[6]+m2[10]*matrix[10]+m2[11]*matrix[14];
		ans[11]=m2[8]*matrix[3]+m2[9]*matrix[7]+m2[10]*matrix[11]+m2[11]*matrix[15];
		
		ans[12]=m2[12]*matrix[0]+m2[13]*matrix[4]+m2[14]*matrix[8]+m2[15]*matrix[12];
		ans[13]=m2[12]*matrix[1]+m2[13]*matrix[5]+m2[14]*matrix[9]+m2[15]*matrix[13];
		ans[14]=m2[12]*matrix[2]+m2[13]*matrix[6]+m2[14]*matrix[10]+m2[15]*matrix[14];
		ans[15]=m2[12]*matrix[3]+m2[13]*matrix[7]+m2[14]*matrix[11]+m2[15]*matrix[15];
		matrix=ans;	
	}
	public void printPosition(){
		Float[] position=new Float[3];
		position[0]=matrix[0]*x+matrix[1]*y+matrix[2]*z+matrix[3];
		position[1]=matrix[4]*x+matrix[5]*y+matrix[6]*z+matrix[7];
		position[2]=matrix[8]*x+matrix[9]*y+matrix[10]*z+matrix[11];
		System.out.println("Position : ("+position[0]+", "+position[1]+", "+position[2]+')');
	}
	public float[] getPosition(){
		float[] position=new float[3];
		position[0]=matrix[0]*x+matrix[1]*y+matrix[2]*z+matrix[3];
		position[1]=matrix[4]*x+matrix[5]*y+matrix[6]*z+matrix[7];
		position[2]=matrix[8]*x+matrix[9]*y+matrix[10]*z+matrix[11];
		return position;
	}
	public float[] getRotation(){
		float[] rotation=new float[3];
		rotation[0]=(float)Math.atan2(-matrix[6], matrix[10]);
		rotation[1]=(float)Math.atan2(matrix[2],Math.sqrt(matrix[0]*matrix[0]+matrix[1]*matrix[1]));
		float cos_t=(float)Math.cos(rotation[0]);
		float sin_t=(float)Math.sin(rotation[0]);
		rotation[2]=(float)Math.atan2(cos_t*matrix[4]+sin_t*matrix[8],cos_t*matrix[5]+sin_t*matrix[9]);
		return rotation;
	}
	public void printRotation(){
		float[]rotation=getRotation();
		System.out.println("Rotation : ("+rotation[0]*180/Math.PI+", "+rotation[1]*180/Math.PI+", "+rotation[2]*180/Math.PI+')');
	}
	public float[] getScale(){
		float[] scale=new float[3];
		scale[0]=(float)Math.sqrt(matrix[0]*matrix[0]+matrix[1]*matrix[1]+matrix[2]*matrix[2]);
		scale[1]=(float)Math.sqrt(matrix[4]*matrix[4]+matrix[5]*matrix[5]+matrix[6]*matrix[6]);
		scale[2]=(float)Math.sqrt(matrix[8]*matrix[8]+matrix[9]*matrix[9]+matrix[10]*matrix[10]);
		return scale;
	}
	public float[] getRotatedVector(float a,float b,float c){
		float[] rotated=new float[3];
		rotated[0]=matrix[0]*a+matrix[1]*b+matrix[2]*c;
		rotated[1]=matrix[4]*a+matrix[5]*b+matrix[6]*c;
		rotated[2]=matrix[8]*a+matrix[9]*b+matrix[10]*c;
		float[] scale=getScale();
		rotated [0]/=scale[0];
		rotated[1]/=scale[1];
		rotated[2]/=scale[2];
		return rotated;
	}
	public void applyReverseOn(List<Float> vertices)
	{
		
		float x,y,z,w;
		for(int i=0;i<vertices.size();){
			x=vertices.get(i);
			y=vertices.get(i+1);
			z=vertices.get(i+2);
			w=vertices.get(i+3);
			vertices.set(i++,x*matrix[0]+y*matrix[4]+z*matrix[8]-w*matrix[3]);
			vertices.set(i++,x*matrix[1]+y*matrix[5]+z*matrix[9]-w*matrix[7]);
			vertices.set(i++,x*matrix[2]+y*matrix[6]+z*matrix[10]-w*matrix[11]);
		}
	}
	public void revert(){
		float tmp;
		tmp=matrix[1];
		matrix[1]=matrix[4];
		matrix[4]=tmp;
		
		tmp=matrix[2];
		matrix[2]=matrix[8];
		matrix[8]=tmp;
		
		tmp=matrix[6];
		matrix[6]=matrix[9];
		matrix[9]=tmp;
//		
//		matrix[3]=-matrix[3];
//		matrix[7]=-matrix[7];
//		matrix[11]=-matrix[11];
	}
	public void apply(Transform t){
		this.multiplyBefore(t.matrix);
	}
	void translateLocal(float x,float y,float z){
		float[] forward=getRotatedVector(x, y, z);
		translate(forward[0], forward[1],forward[2]);
	}
	public void lookAt(Transform t){
		
	}
}
	
  