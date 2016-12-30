package eventsystem;
import java.awt.image.BufferedImage;

import gcore.Camera;
import gcore.FrameBuffer;
import rendercore.RenderManager;

import java.awt.Color;


/**
 * @author sudip
 * Date : Dec 29, 2016
 * --> 
 * I don't know why this class exists!
 * This is simply seems not necessary when FrameBuffer
 * classes (SingleBuffer and DoubleBuffer) will be made.
 * But this class along with the DisplayWindow class
 * will be helping to provide easier interface to javax.swing.*
 * components.
 * May be in future frameBuffer class might be too primitive and
 * ViewPort will be build above the buffer classes for working more
 * close to the native displaying interface.
 */

public class ViewPort extends gcore.Object{

	private int width,  height;
	private gcore.Camera associatedCamera;
	
	/*
	 * Frame Buffer will used for the standard release but for now ultil the release of ver 0.1
	 * we will be using BufferedImage for storing viewport frames. 
	 */
	private java.awt.image.BufferedImage drawArea;
	private FrameBuffer buffer;
	
	
	
	public ViewPort(int width,int height) {
		this.width=width;
		this.height=height;
		drawArea=new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);

	}
	public java.awt.image.BufferedImage getDrawArea() {
		return drawArea;
	}
	void useCamera(Camera camera){
		this.associatedCamera=camera;
	}
	
	public void clear(){
        for (int x = 0; x <drawArea.getWidth(); x++) {
            for (int y = 0; y < drawArea.getHeight(); y++) {
                drawArea.setRGB(x, y,0x252525);
            }
        }
	}
	public void drawPixel(int x,int y,Color color){
		if(x>0&&y>0&&x<width&&y<height)
		drawArea.setRGB(x,height-y, color.getRGB());
	}
	public void drawPixel(int x, int y){
		if(x>0&&y>0&&x<width&&y<height)
		drawArea.setRGB(x,height-y,Color.BLACK.getRGB());
	}
	
	public void resize(int width,int height){
		this.width=width;
		this.height=height;
		drawArea=new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	}
	public void repaint(){
		ViewPort output=new ViewPort(width, height);
		RenderManager.getDefaultManager().getrenderedOutput(output);
		drawArea=output.drawArea;
	}
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	public gcore.Camera getCamera() {
		return associatedCamera;
	}
	
}

