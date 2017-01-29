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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import javax.swing.Timer;
import javax.swing.event.MenuDragMouseEvent;

import com.sun.javafx.scene.paint.GradientUtils.Point;
import com.sun.prism.Image;

import javafx.scene.input.MouseButton;
import sun.reflect.generics.tree.VoidDescriptor;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Display extends javax.swing.JFrame implements ActionListener,MouseMotionListener,MouseListener,MouseWheelListener,ComponentListener{
	private static final long serialVersionUID = 1L;
	static Display display;
	JPanel drawPanel;
	BufferedImage image,buffer;
	Timer timer;
	private int pressedButton;
	private int mouseInitx,mouseInity;
	Robot robot;
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
	public void drawLine(int x,int y,int x2,int y2,int color){
		Graphics graphics=buffer.getGraphics();
		graphics.setColor(new Color(color));
		graphics.drawLine(x, y, x2, y2);
	}
	public BufferedImage getImage(){
		return image;
	}
	public void actionPerformed(ActionEvent e) {
		buffer=new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
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
		else if(pressedButton==MouseEvent.BUTTON3){//right mouse drag event
			Camera.getCamera().rotateOnDrag(mouseInitx-e.getX(),mouseInity-e.getY());
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
	
}
