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
public class TriangleIntegerThreeColorRasterization extends Triangle {
    
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
    protected void fillFlatSideTriangleInt(Graphics g, Vertice v1, Vertice v2, Vertice v3,
            Color c1, Color c2, Color c3)
    {
        Vertice vTmp1 = new Vertice(v1.x, v1.y);
        Vertice vTmp2 = new Vertice(v1.x, v1.y);
                
        boolean changed1 = false;
        boolean changed2 = false;
        
        int dx1 = Math.abs(v2.x - v1.x);
        int dy1 = Math.abs(v2.y - v1.y);
        /* get total color change on edge1 */
        int dRed1 = c2.getRed() - c1.getRed();
        int dGreen1 = c2.getGreen() - c1.getGreen();
        int dBlue1 = c2.getBlue() - c1.getBlue();
        
        int dx2 = Math.abs(v3.x - v1.x);
        int dy2 = Math.abs(v3.y - v1.y);
        /* get total color change on edge2 */
        int dRed2 = c3.getRed() - c1.getRed();
        int dGreen2 = c3.getGreen() - c1.getGreen();
        int dBlue2 = c3.getBlue() - c1.getBlue();
        
        int signx1 = (int)Math.signum(v2.x - v1.x);
        int signx2 = (int)Math.signum(v3.x - v1.x);
                
        int signy1 = (int)Math.signum(v2.y - v1.y);
        int signy2 = (int)Math.signum(v3.y - v1.y);
        
        /* get 'direction' of color change on edge2 */
        int signRed2 =  (int)Math.signum(c3.getRed() - c1.getRed());
        int signGreen2 =  (int)Math.signum(c3.getGreen() - c1.getGreen());
        int signBlue2 =  (int)Math.signum(c3.getBlue() - c1.getBlue());
        
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
        /* get scaled starting color of edge1 */
        int red1 =  c1.getRed() * dx1;
        int green1 =  c1.getGreen() * dx1;
        int blue1 =  c1.getBlue() * dx1;
        /* get scaled starting color of edge2 */
        int red2 =  c1.getRed() * dx1;
        int green2 =  c1.getGreen() * dx1;
        int blue2 =  c1.getBlue() * dx1;
        /* get color change of each step for edge1 */
        int deltaRed1 = dRed1;
        int deltaGreen1 = dGreen1;
        int deltaBlue1 = dBlue1;
        /* get color change of each step for edge2 */
        int deltaRed2 = dRed2;
        int deltaGreen2 = dGreen2;
        int deltaBlue2 = dBlue2;
        
        
        for (int i = 0; i <= dx1; i++)
        {
            /* loop over each pixel of horizontal line */
            /* check if paint goes from left endpoint of horizontal line to right or other way round */
            int leftEndPoint, rightEndPoint;
                        
            if (vTmp1.x < vTmp2.x)
            {
                leftEndPoint = vTmp1.x;
                rightEndPoint = vTmp2.x;
            }
            else
            {
                leftEndPoint = vTmp2.x;
                rightEndPoint = vTmp1.x;
            }
            
            for (int xPos = (int)Math.ceil(leftEndPoint); xPos <= (int)rightEndPoint; xPos++)
            {
                int scale = xPos - leftEndPoint;
                int diff = rightEndPoint - leftEndPoint + 1;
                
                int redTmp = (diff - scale) * red1 + scale * red2;
                int greenTmp = (diff - scale) * green1 + scale * green2;
                int blueTmp = (diff - scale) * blue1 + scale * blue2;
                
                Color color = new Color(redTmp / (diff * dx1), greenTmp / (diff * dx1), blueTmp / (diff * dx1));
                g.setColor(color);        
                g.fillRect(xPos, vTmp1.y, 1, 1); 
            }
            
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
                    
                    red2 += signRed2;
                    blue2 += signBlue2;
                    green2 += signGreen2;
                }

                if (changed2)
                    vTmp2.y += signy2;
                else
                    vTmp2.x += signx2;

                e2 = e2 + 2 * dy2;
            }
            
            /* increase the color values at each edge */
            red1 += deltaRed1;
            green1 += deltaGreen1;
            blue1 += deltaBlue1;
            
            red2 += deltaRed2;
            green2 += deltaGreen2;
            blue2 += deltaBlue2;
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
            fillFlatSideTriangleInt(g, vt1, vt2, vt3, colorV1, colorV2, colorV3);
        }
        /* check for trivial case of top-flat triangle */
        else if (vt1.y == vt2.y)
        {
            fillFlatSideTriangleInt(g, vt3, vt1, vt2, colorV1, colorV2, colorV3);
        } 
        else
        {
            /* general case - split the triangle in a topflat and bottom-flat one */
            Vertice vTmp = new Vertice( 
                (int)(vt1.x + ((float)(vt2.y - vt1.y) / (float)(vt3.y - vt1.y)) * (vt3.x - vt1.x)), vt2.y);
            
            /* get the intermediate color at the temporary point */
            float cBlue = colorV1.getBlue() + ((float)(vt2.y - vt1.y) / (float)(vt3.y - vt1.y)) * (colorV3.getBlue() - colorV1.getBlue());
            float cRed = colorV1.getRed() + ((float)(vt2.y - vt1.y) / (float)(vt3.y - vt1.y)) * (colorV3.getRed() - colorV1.getRed());
            float cGreen = colorV1.getGreen() + ((float)(vt2.y - vt1.y) / (float)(vt3.y - vt1.y)) * (colorV3.getGreen() - colorV1.getGreen());
            Color cTmp = new Color(cRed / 255f, cGreen / 255f, cBlue / 255f);
            
            /* for debugging
            System.out.println("V1.x= " + vt1.x + " V1.y=" + vt1.y + " R=" + colorV1.getRed() + " G=" + colorV1.getGreen() + " B=" + colorV1.getBlue() );
            System.out.println("V2.x= " + vt2.x + " V2.y=" + vt2.y + " R=" + colorV2.getRed() + " G=" + colorV2.getGreen() + " B=" + colorV2.getBlue() );
            System.out.println("V3.x= " + vt3.x + " V3.y=" + vt3.y + " R=" + colorV3.getRed() + " G=" + colorV3.getGreen() + " B=" + colorV3.getBlue() );
            System.out.println("vTmp.x= " + vTmp.x + " vTmp.y=" + vTmp.y + " R=" + cRed + " G=" + cGreen + " B=" + cBlue );
            */
            
            /* as we have to draw each line from left to right, we have to check which point of the horizontal line
             * has a lower x-coordinate and swap them if necessary.
             */
            if (vt2.x < vTmp.x)
            {
                fillFlatSideTriangleInt(g, vt1, vt2, vTmp, colorV1, colorV2, cTmp);
                fillFlatSideTriangleInt(g, vt3, vt2, vTmp, colorV3, colorV2, cTmp);
            }
            else
            {
                fillFlatSideTriangleInt(g, vt1, vTmp, vt2, colorV1, cTmp, colorV2);
                fillFlatSideTriangleInt(g, vt3, vTmp, vt2, colorV3, cTmp, colorV2);
            }
        }
    }
}
