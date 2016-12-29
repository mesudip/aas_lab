package eventsystem;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.EventListener;

import javax.swing.Timer;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
/**
 * @author sudip
 * Date : Dec 29, 2016
 * -->
 * This is the window class where the vewports will be displayed.
 * For initial easy test. There will be only one window. Only one 
 * viewport. The window will have a timer that will trigger a event
 * periodically, each trigger will cause the screen to be refreshed
 * (ie. the rendering cycle will restart from beginning despite the fact
 * that it previous output could be reused.)
 * 
 */
public class DisplayWindow extends javax.swing.JFrame implements ActionListener{
	int reshapeCount;
	private static final long serialVersionUID = -1484538271370666054L;
	javax.swing.JPanel drawPanel;
	ViewPort mainViewPort;
	
	Timer timer;
	@SuppressWarnings("serial")
	public DisplayWindow(int fps) {
		
		super("Output Window");
		mainViewPort=new ViewPort(1000, 600);
		final ViewPort viewPort=mainViewPort=new ViewPort(1000, 600);
		drawPanel=new JPanel(){
			
			public void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        Graphics2D g2 = (Graphics2D) g;
		        g2.drawImage(viewPort.getDrawArea(), null, null);
		    }
		};
		drawPanel.setSize(new Dimension(1000,600));
		drawPanel.setPreferredSize(new Dimension(1000,600));
		getContentPane().add(drawPanel);
		drawPanel.setVisible(true);
		pack();
		setVisible(true);
		//timer=new Timer(1000/fps, this);
	}
	static public void main(String args[]){
		/* @author sudip
		 * Create a window with screen refreshing at the rate of 10 fps
		 */
		DisplayWindow window=new DisplayWindow(10);
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		mainViewPort.repaint();
		drawPanel.repaint();
		
	}

}
