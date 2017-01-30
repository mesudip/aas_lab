/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TriangleRasterisation;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author SUN2K
 */
public class MovablePoint extends javax.swing.JPanel {
    
    /* length of rectangle side representing the point */
    public static final int SIZE = 8;
    /* half of length of rectangle side representing the point */
    public static final int SIZE2 = SIZE / 2;
    
    /* reference to parent panel */
    private static DrawingPanel parent;
    
    /**
     * helper variables to hold previous coordinate of point. 
     * Used to implement dragging of points.
     */
    private int oldX = 0, oldY = 0;
    
    /**
     * Constructor for one point.
     * @param parent reference to drawing panel where the point will be located
     * @param x initial x-coordinate of point
     * @param y initial y-coordinate of point
     */
    public MovablePoint(DrawingPanel parent, int x, int y)
    {
        MovablePoint.parent = parent;
        this.setLocation(x,y);
        this.setSize(SIZE, SIZE);
        this.setBackground(Color.BLACK);
       
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        this.addMouseMotionListener(new MouseAdapter() 
        {
            @Override
            public void mouseMoved(MouseEvent event)
            {
               oldX = event.getX();
               oldY = event.getY();
            }
            
            @Override
            public void mouseDragged(MouseEvent event)
            {
                // prevent draggin outside the applet
                if (getLocation().x + event.getX() > getParentBounds().width ||
                    getLocation().x + event.getX() < 0 ||
                    getLocation().y + event.getY() > getParentBounds().height ||
                    getLocation().y + event.getY() < 0)
                {
                    return;
                }

                setLocation(getLocation().x + event.getX() - oldX, 
                            getLocation().y + event.getY() - oldY);
                repaintParent();
            }
        });
    }
    
    /**
     * Repaint the drawing panel
     */
    public void repaintParent()
    {
        parent.repaint();
    }
    
    /**
     * Helper to access the bounds of this panel from inner class.
     * @return bounds of drawing panel
     */
    public Rectangle getParentBounds()
    {
        return parent.getBounds();
    }
    
    /**
     * Getter for midpoint x-coordinate
     * @return the x-coordinate of absolute center of this point
     */
    public int getMidX() { return getLocation().x + SIZE2; }
    
    /**
     * Getter for midpoint y-coordinate
     * @return the y-coordinate of absolute center of this point
     */
    public int getMidY() { return getLocation().y + SIZE2; }
    
}
