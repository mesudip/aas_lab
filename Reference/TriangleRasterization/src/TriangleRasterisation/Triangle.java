/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TriangleRasterisation;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author SUN2K
 */
public abstract class Triangle {
    
    /* the three vertices of the triangle */
    protected Vertice vt1, vt2, vt3;
    
    /* the color the triangle is drawn */
    protected Color solidColor;
    
    /* in case the derived rasterization class implements an algorithm which uses
     * a different color for each edge, these colors are used
     */
    protected Color colorV1, colorV2, colorV3;
    
    /**
     * Constructor
     */
    public Triangle()
    {
        vt1 = new Vertice();
        vt2 = new Vertice();
        vt3 = new Vertice();
        /* set default values */
        solidColor = new Color(255, 153, 61);
        colorV1 = Color.RED;
        colorV2 = Color.BLUE;
        colorV3 = Color.YELLOW;
        
    }
        
    /**
     * Set new position of vertice 1.
     * @param x new x coordinate
     * @param y new y cooridnate
     */
    public void setPosP1(int x, int y)
    {
        vt1.x = x;
        vt1.y = y;
    }
    
    /**
     * Set new position of vertice 2.
     * @param x new x coordinate
     * @param y new y cooridnate
     */
    public void setPosP2(int x, int y)
    {
        vt2.x = x;
        vt2.y = y;
    }
    
    /**
     * Set new position of vertice 3.
     * @param x new x coordinate
     * @param y new y cooridnate
     */
    public void setPosP3(int x, int y)
    {
        vt3.x = x;
        vt3.y = y;
    }

    /**
     * Draw the border of the triangle.
     * @param g Graphics object
     */
    public void draw(Graphics g) {
        g.drawLine(vt1.x, vt1.y, vt2.x, vt2.y);
        g.drawLine(vt2.x, vt2.y, vt3.x, vt3.y);
        g.drawLine(vt3.x, vt3.y, vt1.x, vt1.y);
    }
    
    /**
     * Draw a filled triangle. Implemented in subclasses with different algorithms.
     * @param g Graphics object
     */
    public abstract void rasterize(Graphics g);
    
    /**
     * Sort the three vertices ascending by y-coordinate.
     * After calling following apples: vt1.y <= vt2.y <= vt3.y
     */
    protected void sortVerticesAscendingByY()
    {
        Vertice     vTmp;
        
        if (vt1.y > vt2.y)
        {
            vTmp = vt1;
            vt1 = vt2;
            vt2 = vTmp;
        }
        /* here v1.y <= v2.y */
        if (vt1.y > vt3.y)
        {
            vTmp = vt1;
            vt1 = vt3;
            vt3 = vTmp;
        }
        /* here v1.y <= v2.y and v1.y <= v3.y so test v2 vs. v3 */
        if (vt2.y > vt3.y)
        {
            vTmp = vt2;
            vt2 = vt3;
            vt3 = vTmp;
        }
    }

    /**
     * Get the color assigned to the traingle in solid color mode.
     * @return color of triangle
     */
    public Color getSolidColor() 
    {
        return solidColor;
    }
    
    /**
     * Set the solid triangle color.
     * @param c new color
     */
    public void setSolidColor(Color c)
    {
        solidColor = c;
    }
    
    public Color getColorV1()
    {
        return colorV1;
    }
    
    public void setColorV1(Color c)
    {
        colorV1 = c;
    }
    
    public Color getColorV2()
    {
        return colorV2;
    }
    
    public void setColorV2(Color c)
    {
        colorV2 = c;
    }
    
    public Color getColorV3()
    {
        return colorV3;
    }
    
    public void setColorV3(Color c)
    {
        colorV3 = c;
    }
    
    /**
     * Calculates crossProduct of two 2D vectors / points.
     * @param p1 first point used as vector
     * @param p2 second point used as vector
     * @return crossProduct of vectors
     */
    protected int crossProduct(Vertice v1, Vertice v2)
    {
        return (v1.x * v2.y - v1.y * v2.x);
    }
    
    
    /**
     * Class representing a vertice of a triangle.
     */
    public class Vertice {
        public int x;
        public int y;
        
        public Vertice() 
        {
            this(0, 0);
        }
        
        public Vertice(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }
}
