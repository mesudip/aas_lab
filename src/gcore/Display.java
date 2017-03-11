package gcore;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.nio.CharBuffer;
import java.util.List;
import java.util.stream.Stream;

import javax.swing.Timer;
import javax.swing.event.MenuDragMouseEvent;

import com.sun.corba.se.impl.ior.ByteBuffer;
import com.sun.corba.se.spi.ior.iiop.AlternateIIOPAddressComponent;
import com.sun.javafx.geom.FlatteningPathIterator;
import com.sun.javafx.scene.paint.GradientUtils.Point;

import com.sun.org.apache.xpath.internal.axes.UnionPathIterator;
import com.sun.org.apache.xpath.internal.operations.And;
import com.sun.prism.Image;
import com.sun.xml.internal.bind.v2.model.core.NonElement;

import gcomposite.DynamicObject3d;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.transform.Rotate;
import jdk.internal.dynalink.beans.StaticClass;
import jdk.nashorn.internal.ir.WhileNode;
import project.Main;

import sun.reflect.generics.tree.VoidDescriptor;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Display extends javax.swing.JFrame implements ActionListener,MouseMotionListener,MouseListener,MouseWheelListener,ComponentListener,KeyListener,DropTargetListener{
	private static final long serialVersionUID = 1L;
	long keymasks;
	public enum EditorKey{
		Up(1<<0),
		Down(1<<1),
		Left(1<<2),
		Right(1<<3),		
		Rotate(1<<4),
		Translate(1<<5),
		Scale(1<<6),
		Grab(1<<10),
		x(1<<7),
		y(1<<8),
		z(1<<9),
		None(11),
		Alt(12),
		Ctrl(13),
		Shift(14);
private int key;
		EditorKey( int val){
			key=val;
		}
		public int getKey(){
			return key;
		}
		void mask(EditorKey key){
			this.key|=key.getKey();
		}
		void clear(){
			key=0;
		}
		boolean has(EditorKey key){
			return (this.key & key.getKey())!=0;
		}
		void unmask(EditorKey key){
			this.key &=(~key.getKey());
		}
	};
	static Display display;
	JPanel drawPanel;
	BufferedImage image,buffer;
	Timer timer;
	private int pressedButton;
	private int mouseInitx,mouseInity;
	float[][] zbuffer;
	
	Robot robot;
	int color;
	boolean freeCamera=false;
	boolean keyMasked=false;
	int keyMask;
	
	EditorKey activeKey=EditorKey.None;
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
		//final Display _Display=this;
		drawPanel.setDropTarget(new DropTarget(this, this));
		//drawPanel.addMouseMotionListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	static public void initialize(){
		display=new Display();
		display.setVisible(true);
		display.startTimer();
	}
	private void startTimer(){
		timer=new Timer(5, this);
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
		
		long time=System.currentTimeMillis();
		Main.onUpdate();
		buffer=new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
		zbuffer=new float[this.getWidth()][this.getHeight()];
		for(int i=0;i<zbuffer.length;i++){	
			for(int j=0;j<zbuffer[i].length;j++){
				zbuffer[i][j]=Float.POSITIVE_INFINITY;
			}
		}
		

		Graphics g=buffer.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, buffer.getWidth(), buffer.getHeight());
		
		
		Object3d.render(buffer.getWidth(),buffer.getHeight());
		this.image=buffer;
		drawPanel.repaint();
		time=System.currentTimeMillis()-time;
		setTitle(String.format("Output window : [min render time :%03d ms ] [fps :%03.2f ]",time,((float)1)/time*1000));
	}
	
	static public Display getDisplay(){
		return display;
	}
	public void mouseMoved(MouseEvent e) {
		
	       if(freeCamera){
	    	   System.out.println("This is going");
	    	   Camera.getCamera().freeMouseRotation(e.getX()-mouseInitx,e.getY()-mouseInity);
	       }
	       mouseInitx=e.getX();mouseInity=e.getY();
	}
	
	public void mouseDragged(MouseEvent e) {
		
		if(pressedButton==MouseEvent.BUTTON1){
			Camera.getCamera().getTransform().translate(e.getX()-mouseInitx, e.getY()-mouseInity, 0);
		}
		else if(pressedButton==MouseEvent.BUTTON3){//right mouse drag event
			if(keyMasked){
				if(keyMask==KeyEvent.VK_X)
					Camera.getCamera().transform.rotatex(e.getX()-mouseInitx);
					//Camera.getCamera().rotatexOnDrag(e.getX()-mouseInitx,e.getY()-mouseInity);
				else if(keyMask==KeyEvent.VK_Y)
					Camera.getCamera().transform.rotatey(e.getX()-mouseInitx);
					//Camera.getCamera().rotateyOnDrag(e.getX()-mouseInitx,e.getY()-mouseInity);
				else if(keyMask==KeyEvent.VK_Z)
					Camera.getCamera().transform.rotatez(e.getX()-mouseInitx);
					//Camera.getCamera().rotatezOnDrag(e.getX()-mouseInitx,e.getY()-mouseInity);
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
		//drawPanel.reshape(0, 0, e.getComponent().getWidth(), e.getComponent().getHeight());
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
		//System.out.println("Key pressed :"+e.getKeyCode()+", "+(char)e.getKeyCode());
		switch(e.getKeyCode()){
			case KeyEvent.VK_W:
			case KeyEvent.VK_UP:
				activeKey.mask(EditorKey.Up);
				Camera.getCamera().transform.translateLocal(0, 0, 10);
				break;
			case KeyEvent.VK_S :
			case KeyEvent.VK_DOWN:
				activeKey.mask(EditorKey.Down);
				Camera.getCamera().transform.translateLocal(00, 0, -10);
				break;
			case KeyEvent.VK_A:
			case KeyEvent.VK_LEFT:
				activeKey.mask(EditorKey.Left);
				Camera.getCamera().transform.translateLocal(0, -10, 0);
				break;
			case KeyEvent.VK_D:
			case KeyEvent.VK_RIGHT:
				activeKey.mask(EditorKey.Right);
				Camera.getCamera().transform.translateLocal(0, 10, 0);
				break;
			case KeyEvent.VK_C:
				System.out.print("c key activated");
				if(e.getKeyCode()==KeyEvent.VK_ALT){
					System.out.println(" : With alt modifier");
					freeCamera=!freeCamera;
				}
				break;
			case KeyEvent.VK_ALT:
				activeKey.mask(EditorKey.Alt);
				break;
			case KeyEvent.VK_CONTROL:
				activeKey.mask(EditorKey.Ctrl);
				break;
			case KeyEvent.VK_SHIFT:
				activeKey.mask(EditorKey.Shift);
				break;
			default:
				keyMasked=true;
			
		}
		
	}
	public void keyReleased(KeyEvent e) {
		System.out.println("Key released");
		keyMasked=false;
		
		// TODO Auto-generated method stub
		
	}
	@Override
	public void dragEnter(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub	
	}
	@Override
	public void dragOver(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void dropActionChanged(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void dragExit(DropTargetEvent dte) {
		// TODO Auto-generated method stub
			}
	@Override
	public void drop(DropTargetDropEvent dtde) {
		System.out.println("Drop Event occured"+dtde.toString());
		dtde.getCurrentDataFlavorsAsList();
		try{
			dtde.acceptDrop(DnDConstants.ACTION_COPY);
			List<File> droppedFiles=(List<File>)dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
			if(!droppedFiles.isEmpty()){
				new DynamicObject3d(droppedFiles.get(0));
			}
		}
		catch(Exception ex){
			System.out.println("Invalid Drop :"+ex.getMessage());
			ex.printStackTrace();
			
		}
	}
	
}
