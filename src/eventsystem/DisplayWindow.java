package eventsystem;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
public class DisplayWindow extends javax.swing.JFrame{
	int reshapeCount;
	private static final long serialVersionUID = -1484538271370666054L;
	javax.swing.JPanel drawPanel;
	ViewPort mainViewPort;
	
	@SuppressWarnings("serial")
	public DisplayWindow() {
		
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
		viewPort.drawPixel(100, 100,Color.BLUE);
		drawPanel.setPreferredSize(new Dimension(1000,600));
		drawPanel.setSize(new Dimension(1000,600));
		getContentPane().add(drawPanel);
		drawPanel.setVisible(true);
		pack();
		setVisible(true);
	}
	static public void main(String args[]){
		
		DisplayWindow window=new DisplayWindow();
		
		
	}

}
