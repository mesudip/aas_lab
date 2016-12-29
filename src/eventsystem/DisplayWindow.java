package eventsystem;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JWindow;
import java.awt.Color;
import javafx.embed.swing.*;
public class DisplayWindow extends JFrame{

	public DisplayWindow(String title) {
		super(title);
	
	}
	static public void main(String args[]){
		DisplayWindow window=new DisplayWindow("Output Window");
		window.setSize(1000,600);
		
		WindowCanvas canvas=new WindowCanvas(800,400);
		
		window.getContentPane().add(canvas);
		canvas.setVisible(true);
		window.pack();
		window.setVisible(true);
	}

}
class WindowCanvas extends JPanel{
	private static final long serialVersionUID = -6913318218275264803L;
	private BufferedImage canvas;

	    public WindowCanvas(int width, int height) {
	        canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	        fillCanvas(Color.BLUE);   
	    }

	    public Dimension getPreferredSize() {
	        return new Dimension(canvas.getWidth(), canvas.getHeight());
	    }

	    public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        Graphics2D g2 = (Graphics2D) g;
	        g2.drawImage(canvas, null, null);
	    }

	    public void fillCanvas(Color c) {
	        int color = c.getRGB();
	        for (int x = 0; x < canvas.getWidth(); x++) {
	            for (int y = 0; y < canvas.getHeight(); y++) {
	                canvas.setRGB(x, y, color);
	            }
	        }
	        repaint();
	    }
}
