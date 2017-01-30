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
public class TriangleIntegerSolidRasterization extends Triangle {
    
    /**
     * This method rasterizes a triangle using only integer variables by using a
     * modified bresenham algorithm.
     * It's important that v2 and v3 lie on the same horizontal line, that is
     * v2.y must be equal to v3.y.
     * @param g Graphics object
     * @param v1 vertice of triangle
     * @param v2 vertice of triangle, must have same y-coordinate as v3.
     * @param v3 vertice of triangle, must have same y-coordinate as v2.
     */
    protected void fillFlatSideTriangleInt(Graphics g, Vertice v1, Vertice v2, Vertice v3)
    {
        Vertice vTmp1 = new Vertice(v1.x, v1.y);
        Vertice vTmp2 = new Vertice(v1.x, v1.y);
        
        boolean changed1 = false;
        boolean changed2 = false;
        
        int dx1 = Math.abs(v2.x - v1.x);
        int dy1 = Math.abs(v2.y - v1.y);
        
        int dx2 = Math.abs(v3.x - v1.x);
        int dy2 = Math.abs(v3.y - v1.y);
        
        int signx1 = (int)Math.signum(v2.x - v1.x);
        int signx2 = (int)Math.signum(v3.x - v1.x);
        
        int signy1 = (int)Math.signum(v2.y - v1.y);
        int signy2 = (int)Math.signum(v3.y - v1.y);
        
        if (dy1 > dx1)
        {   // swap values
            int tmp = dx1;
            dx1 = dy1;
            dy1 = tmp;
            changed1 = true;
        }
        
        if (dy2 > dx2)
        {   // swap values
            int tmp = dx2;
            dx2 = dy2;
            dy2 = tmp;
            changed2 = true;
        }
        
        int e1 = 2 * dy1 - dx1;
        int e2 = 2 * dy2 - dx2;
        
        for (int i = 0; i <= dx1; i++)
        {
            g.drawLine(vTmp1.x, vTmp1.y, vTmp2.x, vTmp2.y);
            
            while (e1 >= 0)
            {
                if (changed1)
                    vTmp1.x += signx1;
                else
                    vTmp1.y += signy1;
                e1 = e1 - 2 * dx1;
            }
            
            if (changed1)
                vTmp1.y += signy1;
            else
                vTmp1.x += signx1;  
          
            e1 = e1 + 2 * dy1;
            
            /* here we rendered the next point on line 1 so follow now line 2
             * until we are on the same y-value as line 1.
             */
            while (vTmp2.y != vTmp1.y)
            {
                while (e2 >= 0)
                {
                    if (changed2)
                        vTmp2.x += signx2;
                    else
                        vTmp2.y += signy2;
                    e2 = e2 - 2 * dx2;
                }

                if (changed2)
                    vTmp2.y += signy2;
                else
                    vTmp2.x += signx2;

                e2 = e2 + 2 * dy2;
            }
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
            fillFlatSideTriangleInt(g, vt1, vt2, vt3);
        }
        /* check for trivial case of top-flat triangle */
        else if (vt1.y == vt2.y)
        {
            fillFlatSideTriangleInt(g, vt3, vt1, vt2);
        } 
        else
        {
            /* general case - split the triangle in a topflat and bottom-flat one */
            Vertice vTmp = new Vertice( 
                (int)(vt1.x + ((float)(vt2.y - vt1.y) / (float)(vt3.y - vt1.y)) * (vt3.x - vt1.x)), vt2.y);
            fillFlatSideTriangleInt(g, vt1, vt2, vTmp);
            fillFlatSideTriangleInt(g, vt3, vt2, vTmp);
        }
    }
}
