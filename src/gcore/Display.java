package gcore;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import javax.swing.Timer;
import javax.swing.event.MenuDragMouseEvent;

import com.sun.javafx.geom.FlatteningPathIterator;
import com.sun.javafx.scene.paint.GradientUtils.Point;
import com.sun.javafx.sg.prism.web.NGWebView;
import com.sun.org.apache.xpath.internal.operations.And;
import com.sun.prism.Image;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import project.Main;
import sun.reflect.generics.tree.VoidDescriptor;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Display extends javax.swing.JFrame implements ActionListener,MouseMotionListener,MouseListener,MouseWheelListener,ComponentListener,KeyListener{
	private static final long serialVersionUID = 1L;
	static Display display;
	JPanel drawPanel;
	BufferedImage image,buffer;
	Timer timer;
	private int pressedButton;
	private int mouseInitx,mouseInity;
	float[][] zbuffer;
	
	Robot robot;
	int color;
	
	boolean keyMasked=false;
	int keyMask;
	public void setColor(int color){
		this.color=color;
	}
	private Display(){
		super("Output Window");
		
		try {
			robot=new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			System.err.println("Error :Your display doesn't support low level input Control");
		}
		final Display display=this;
		drawPanel=new JPanel(){
			private static final long serialVersionUID = 2L;
			public void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        Graphics2D g2 = (Graphics2D) g;
		        g2.drawImage(display.getImage(), null, null);
		    }
		};
		
		drawPanel.setSize(new Dimension(1000,600));
		drawPanel.setPreferredSize(new Dimension(1000,600));
		getContentPane().add(drawPanel);
		image=new BufferedImage(1000, 600, BufferedImage.TYPE_INT_ARGB);
		drawPanel.setVisible(true);
		pack();
		addMouseMotionListener(this);
		addMouseListener(this);
		addMouseWheelListener(this);
		addComponentListener(this);
		addKeyListener(this);
		//drawPanel.addMouseMotionListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	static public void initialize(){
		display=new Display();
		display.setVisible(true);
		display.startTimer();;
	}
	private void startTimer(){
		timer=new Timer(10, this);
		timer.start();
	}
	public void drawPixel(int x,int y,int color){
		buffer.setRGB(x, y, color);
	}
	public void drawPixel(int x,int y,float z){
		
		if(x>=0 && y>=0 && x<zbuffer.length && y<zbuffer[0].length){
			if(zbuffer[x][y]>z){
				buffer.setRGB(x, y, color);
				zbuffer[x][y]=z;
			}
		}
	}
	public void drawLine(int x,int y,int x2,int y2,int color){
		Graphics graphics=buffer.getGraphics();
		graphics.setColor(new Color(color));
		graphics.drawLine(x, y, x2, y2);
	}
	public BufferedImage getImage(){
		return image;
	}
	synchronized public void actionPerformed(ActionEvent e) {
		Main.onUpdate();
		buffer=new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
		zbuffer=new float[this.getWidth()][this.getHeight()];
		for(int i=0;i<zbuffer.length;i++){
			for(int j=0;j<zbuffer[i].length;j++){
				zbuffer[i][j]=Float.POSITIVE_INFINITY;
			}
		}
		
		Object3d.render();
		this.image=buffer;
		drawPanel.repaint();
		
		
	}
	
	static public Display getDisplay(){
		return display;
	}
	public void mouseMoved(MouseEvent e) {
	       
	}
	
	public void mouseDragged(MouseEvent e) {
		
		if(pressedButton==MouseEvent.BUTTON1){
			Camera.getCamera().getTransform().translate(e.getX()-mouseInitx, e.getY()-mouseInity, 0);
		}
		else if(pressedButton==MouseEvent.BUTTON3){//right mouse drag event
			if(keyMasked){
				if(keyMask==KeyEvent.VK_X)
				Camera.getCamera().rotatexOnDrag(e.getX()-mouseInitx,e.getY()-mouseInity);
				else if(keyMask==KeyEvent.VK_Y)
					Camera.getCamera().rotateyOnDrag(e.getX()-mouseInitx,e.getY()-mouseInity);
				else if(keyMask==KeyEvent.VK_Z)
					Camera.getCamera().rotatezOnDrag(e.getX()-mouseInitx,e.getY()-mouseInity);
			}
		}
		mouseInitx=e.getX();
		mouseInity=e.getY();
		java.awt.Point absolute=MouseInfo.getPointerInfo().getLocation();
		Dimension dimension=java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		if(mouseInitx<5){
			mouseInitx=getWidth()-5;
		}
		else if(mouseInitx>getWidth()-5){
			mouseInitx=5;
		}
		if(mouseInity<5){
			mouseInity=getHeight()-5;
		}
		else if(mouseInity>getHeight()-5){
			mouseInity=5;
		}
		
		if(mouseInitx!=e.getX()||mouseInity!=e.getY()){
			java.awt.Point location=getLocationOnScreen();
			robot.mouseMove(location.x+mouseInitx, location.y+mouseInity);
		}
		
			
	}
	public void mouseClicked(MouseEvent e) {
		
		// TODO Auto-generated method stub
		
	}
	
	public void mousePressed(MouseEvent e) {
		System.err.println("Button pushed   :("+e.getX()+","+e.getY()+") ->"+e.getButton());
		pressedButton=e.getButton();
		mouseInitx=e.getX();
		mouseInity=e.getY();
		if(pressedButton==MouseEvent.BUTTON1){
			this.setCursor(Cursor.MOVE_CURSOR);
			
		}
	
	}
	public void mouseReleased(MouseEvent e) {
		System.err.println("Button released :("+e.getX()+","+e.getY()+") ->"+e.getButton());
		this.setCursor(Cursor.DEFAULT_CURSOR);
	}
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseWheelMoved(MouseWheelEvent e) {
		
		System.err.println("Mouse Wheel Action : ("+e.getX()+","+e.getY()+") -> Rotation :"+e.getWheelRotation());
		Camera.getCamera().zoom(e.getWheelRotation());
		// TODO Auto-generated method stub
		
	}
	public void componentResized(ComponentEvent e) {
		System.out.println("The component has been resized");
		drawPanel.reshape(0, 0, e.getComponent().getWidth(), e.getComponent().getHeight());
		// TODO Auto-generated method stub
		
	}
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
	}
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void keyPressed(KeyEvent e) {
		keyMask=e.getKeyCode();
		keyMasked=true;
		System.out.println("Key pressed :"+e.getKeyCode()+", "+(char)e.getKeyCode());
		// TODO Auto-generated method stub
		
	}
	public void keyReleased(KeyEvent e) {
		System.out.println("Key released");
		keyMasked=false;
		
		// TODO Auto-generated method stub
		
	}
	
}
