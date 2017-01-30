/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TriangleRasterisation;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author SUN2K
 */
public class DrawingPanel extends JPanel {
    
    /**
     * enumeration of all available algorithms
     */
    public enum TRIANGLE_ALGORITHM { STANDARD, INTEGER, BARYCENTRIC }
    
    /**
     * options how to fill the triangle
     */
    public enum COLOR_MODE { SOLID, FLUXIONARY }
    
    /**
     * background color
     */
    private final static Color backgroundColor = new Color(0, 120, 255);
    
    /**
     * hold the three movable vertices of the triangle
     */
    private MovablePoint[] trianglePoints;
    
    /**
     * actual triangle object
     */
    private Triangle triangle;
    
    /**
     * chosen mode on how to fill the triangle
     */
    private COLOR_MODE colorMode;
    
    /**
     * chosen algorithm to rasterize the triangle
     */
    private TRIANGLE_ALGORITHM triangleAlgorithm;
       
    /**
     * Creates a new instance of DrawingPanel
     * @param width initial width of panel
     * @param height initial width of height 
     */
    public DrawingPanel(int width, int height) {
        this.setLayout(null);
        this.setBounds(0, 0, width, height);
        this.setBackground(backgroundColor);
        
        /* initialize the beginning triangle */
        trianglePoints = new MovablePoint[3];
        trianglePoints[0] = new MovablePoint(this, width / 2, height / 10);
        this.add(trianglePoints[0]);
        trianglePoints[1] = new MovablePoint(this, width / 10 * 3, height / 10 * 8);
        this.add(trianglePoints[1]);
        trianglePoints[2] = new MovablePoint(this, width / 10 * 7, height / 10 * 8);
        this.add(trianglePoints[2]);
        
        /* set default algorithm and color mode */
        colorMode = COLOR_MODE.SOLID;
        triangleAlgorithm = TRIANGLE_ALGORITHM.STANDARD;
        
        /* instantiate default triangle type */
        triangle = new TriangleStandardSolidRasterization();
        /* force paint */
        this.invalidate();
    }
    
    /**
     * Override for easy painting.
     */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        /* set the position of the movable points to the vertices of the triangle */
        /* the decision here is necessary because we also sort the vertices of the triangle later in the rasterization algorithm
           but the connection movablepoint[x] <-> vertice[x] must not be destroyed in order to be consistent what is vertice v2
         * (the middle vertice) visible on the panel and the middle vertice used in the algorithm. */
        /* this does not affect the actual algorithms but which color has which vertice on the applet */
        if (trianglePoints[0].getMidY() > trianglePoints[1].getMidY())
        {
            MovablePoint mpTmp = trianglePoints[0];
            trianglePoints[0] = trianglePoints[1];
            trianglePoints[1] = mpTmp;
            
            Color cTmp = triangle.colorV1;
            triangle.colorV1 = triangle.colorV2;
            triangle.colorV2 = cTmp;
        }
        if (trianglePoints[0].getMidY() > trianglePoints[2].getMidY())
        {
            MovablePoint mpTmp = trianglePoints[0];
            trianglePoints[0] = trianglePoints[2];
            trianglePoints[2] = mpTmp;
            
            Color cTmp = triangle.colorV1;
            triangle.colorV1 = triangle.colorV3;
            triangle.colorV3 = cTmp;
        }
        if (trianglePoints[1].getMidY() > trianglePoints[2].getMidY())
        {
            MovablePoint mpTmp = trianglePoints[1];
            trianglePoints[1] = trianglePoints[2];
            trianglePoints[2] = mpTmp;
           
            Color cTmp = triangle.colorV2;
            triangle.colorV2 = triangle.colorV3;
            triangle.colorV3 = cTmp;
        }
        
        triangle.setPosP1(trianglePoints[0].getMidX(), trianglePoints[0].getMidY());
        triangle.setPosP2(trianglePoints[1].getMidX(), trianglePoints[1].getMidY());
        triangle.setPosP3(trianglePoints[2].getMidX(), trianglePoints[2].getMidY());
               
        /* fill the triangle */
        triangle.rasterize(g);
    }
    
    /**
     * Select used rasterization algorithm
     * @param algo new algorihtm
     */
    public void setRasterizationAlgorithm(TRIANGLE_ALGORITHM algo)
    {
        this.triangleAlgorithm = algo;
        setTriangleClass();
    }
    
    /**
     * Set new color mode to fill the triangle.
     * @param mode new color mode
     */
    public void setColorMode(COLOR_MODE mode)
    {
        this.colorMode = mode;
        setTriangleClass();
    }
    
    /**
     * Internal reinitialization function if a new color mode or algorithm is selected.
     */
    private void setTriangleClass()
    {
        /* save old colors */
        Color solidC = triangle.getSolidColor();
        Color cv1 = triangle.getColorV1();
        Color cv2 = triangle.getColorV2();
        Color cv3 = triangle.getColorV3();
        
        switch (triangleAlgorithm)
        {
            case STANDARD:
                switch (colorMode)
                {
                    case SOLID:
                        triangle = new TriangleStandardSolidRasterization();
                        break;
                        
                    case FLUXIONARY:
                        triangle = new TriangleStandardThreeColorRasterization();
                        break;
                        
                    default: break;
                }
                break;
                
                
            case INTEGER:
                switch (colorMode)
                {
                    case SOLID:
                        triangle = new TriangleIntegerSolidRasterization();
                        break;
                        
                    case FLUXIONARY:
                        triangle = new TriangleIntegerThreeColorRasterization();
                        break;
                        
                    default: break;
                }
                break;
                
            case BARYCENTRIC:
                switch (colorMode)
                {
                    case SOLID:
                        triangle = new TriangleBarycentricSolidRasterization();
                        break;
                        
                    case FLUXIONARY:
                        triangle = new TriangleBarycentricThreeColorRasterization();
                        break;
                        
                    default: break;
                }
                break;
                
            default: break;
        }
        
        /* restore colors */
        triangle.setSolidColor(solidC);
        triangle.setColorV1(cv1);
        triangle.setColorV2(cv2);
        triangle.setColorV3(cv3);
        this.repaint();
    }
    
    /**
     * Show / Hide the movable rectangles of the triangle. 
     * @param visible true to show the vertices, false to hide
     */
    public void setVerticeVisibility(boolean visible)
    {
        trianglePoints[0].setVisible(visible);
        trianglePoints[1].setVisible(visible);
        trianglePoints[2].setVisible(visible);
    }
    
       
    public void setSolidColor(Color c)
    {
        if (c != null)
        {
            triangle.setSolidColor(c);
            this.repaint();
        }
    }
    
    public Color getSolidColor()
    {
        return triangle.getSolidColor();
    }
    
    public void setColorV1(Color c)
    {
        if (c != null)
        {
            triangle.setColorV1(c);
            this.repaint();
        }
    }
    
    public Color getColorV1()
    {
        return triangle.getColorV1();
    }
    
    public void setColorV2(Color c)
    {
        if (c != null)
        {
            triangle.setColorV2(c);
            this.repaint();
        }
    }
    
    public Color getColorV2()
    {
        return triangle.getColorV2();
    }
    
    public void setColorV3(Color c)
    {
        if (c != null)
        {
            triangle.setColorV3(c);
            this.repaint();
        }
    }
    
    public Color getColorV3()
    {
        return triangle.getColorV3();
    }
        
}
