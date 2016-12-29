package rendercore;
import gprimitive.*;

/**
 * @author sudip
 * Date : Dec 29, 2016
 * -->
 * This class will be the registry point.
 * All objects will register themselves for rendering in their constructor.
 * Then in the draw() function of Drawable Objects, they will register
 * all the primitive types like the vertices, edges, faces, textures, etc.
 */
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
