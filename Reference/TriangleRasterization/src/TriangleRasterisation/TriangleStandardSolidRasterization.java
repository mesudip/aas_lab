/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TriangleRasterisation;

import java.awt.Graphics;

/**
 *
 * @author SUN2K
 */
public class TriangleStandardSolidRasterization extends Triangle {
        
    /**
     * Fills a triangle whose bottom side is perfectly horizontal.
     * Precondition is that v2 and v3 perform the flat side and that v1.y < v2.y, v3.y.
     * @param g Graphics object 
     * @param v1 first vertice, has the smallest y-coordinate
     * @param v2 second vertice
     * @param v3 third vertice
     */
    protected void fillBottomFlatTriangle(Graphics g, Vertice v1, Vertice v2, Vertice v3)
    {
        float slope1 = (float)(v2.x - v1.x) / (float)(v2.y - v1.y);
        float slope2 = (float)(v3.x - v1.x) / (float)(v3.y - v1.y);

        float x1 = v1.x;
        float x2 = v1.x + 0.5f;

        for (int scanlineY = v1.y; scanlineY <= v2.y; scanlineY++)
        {
            g.drawLine((int)x1, scanlineY, (int)x2, scanlineY);
            x1 += slope1;
            x2 += slope2;

        }
    }
    
    /**
     * Fills a triangle whose top side is perfectly horizontal.
     * Precondition is that v1 and v2 perform the flat side and that v3.y > v1.y, v2.y.
     * @param g Graphics object 
     * @param v1 first vertice
     * @param v2 second vertice
     * @param v3 third vertice, has the largest y-coordinate
     */
    protected void fillTopFlatTriangle(Graphics g, Vertice v1, Vertice v2, Vertice v3)
    {
        float slope1 = (float)(v3.x - v1.x) / (float)(v3.y - v1.y);
        float slope2 = (float)(v3.x - v2.x) / (float)(v3.y - v2.y);

        float x1 = v3.x;
        float x2 = v3.x + 0.5f;

        for (int scanlineY = v3.y; scanlineY > v1.y; scanlineY--)
        {
            g.drawLine((int)x1, scanlineY, (int)x2, scanlineY);
            x1 -= slope1;
            x2 -= slope2;
        }
    }

    @Override
    public void rasterize(Graphics g) {
        
        g.setColor(solidColor);
        
        /* at first sort the three vertices by y-coordinate ascending, 
         * so p1 is the topmost vertice */
        sortVerticesAscendingByY();
        
        /* here we know that v1.y <= v2.y <= v3.y */
        /* check for trivial case of bottom-flat triangle */
        if (vt2.y == vt3.y)
        {
            fillBottomFlatTriangle(g, vt1, vt2, vt3);
        }
        /* check for trivial case of top-flat triangle */
        else if (vt1.y == vt2.y)
        {
            fillTopFlatTriangle(g, vt1, vt2, vt3);
        } 
        else
        {
            /* general case - split the triangle in a topflat and bottom-flat one */
            Vertice vTmp = new Vertice( 
                (int)(vt1.x + ((float)(vt2.y - vt1.y) / (float)(vt3.y - vt1.y)) * (vt3.x - vt1.x)), vt2.y);
            fillBottomFlatTriangle(g, vt1, vt2, vTmp);
            fillTopFlatTriangle(g, vt2, vTmp, vt3);
        }
        
    }
}
