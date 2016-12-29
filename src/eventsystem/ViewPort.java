package eventsystem;
import java.awt.image.BufferedImage;
import java.beans.DefaultPersistenceDelegate;

import gcore.Camera;
import gcore.FrameBuffer;

import java.awt.Color;


/**
 * @author sudip
 * Date : Dec 29, 2016
 * 
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
		drawArea.setRGB(x, y, color.getRGB());
	}
	public void drawPixel(int x, int y){
		drawArea.setRGB(x,y,Color.BLACK.getRGB());
	}
	
	public void resize(int width,int height){
		this.width=width;
		this.height=height;
		drawArea=new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	}
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public gcore.Camera getCamera() {
		return associatedCamera;
	}
	


}

