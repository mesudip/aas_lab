package rendercore;
import gprimitive.*;

public class RenderRegistry extends gcore.Object {
	void registerPoint(Point3d point){}
	void registerPoint(Point3d[] points){}
	void registerLine(Line3d line){}
	void registerLine(Line3d[] line){}
	void registerTriangle(Triangle3d triangle){}
	void registerTriangle(Triangle3d[] triangles){}
	void registerQuad(Quad3d quad){}
	void registerQuad(Quad3d[] quads){}
	int registerVectorArray(){return 0;}
	int registerColorArray(){return 0;}
}
