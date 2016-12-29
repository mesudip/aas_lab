package rendercore;




/**
 * @author sudip
 * Date : Dec 29, 2016
 * This enum specifies how the object will be rendered on screen.
 * Each drawable object will have a RenderStage value assigned to it.
 * There will be a global RenderStage value with which overall render type can be changed
 */
public enum RenderStage {
	None,Vertices,Wireframe,VerticesAndEdges,Solid,Textured,Shawoded
}
