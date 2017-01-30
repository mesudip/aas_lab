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
public class TriangleStandardThreeColorRasterization extends Triangle {
    
    /**
     * Fills a triangle whose bottom side is perfectly horizontal.
     * Precondition is that v2 and v3 perform the flat side and that v1.y < v2.y, v3.y.
     * @param g Graphics object 
     * @param v1 first vertice, has the smallest y-coordinate
     * @param v2 second vertice
     * @param v3 third vertice
     */
    protected void fillBottomFlatTriangle(Graphics g, Vertice v1, Vertice v2, Vertice v3,
                                                      Color c1, Color c2, Color c3)
    {
        float slope1 = (float)(v2.x - v1.x) / (float)(v2.y - v1.y);
        float slope2 = (float)(v3.x - v1.x) / (float)(v3.y - v1.y);
        
        float x1 = v1.x;
        float x2 = v1.x + 0.5f;
        
        /* get the change of color components along edge (v2,v1) */
        float v2v1Diff = (float)(v2.y - v1.y);
        float colorSlopeBlue1 = (float)(c2.getBlue() - c1.getBlue()) / v2v1Diff;
        float colorSlopeRed1 = (float)(c2.getRed() - c1.getRed()) / v2v1Diff;
        float colorSlopeGreen1 = (float)(c2.getGreen() - c1.getGreen()) / v2v1Diff;
        /* get the change of color components along edge (v3,v1) */
        float v3v1Diff = (float)(v3.y - v1.y);
        float colorSlopeBlue2 = (float)(c3.getBlue() - c1.getBlue()) / v3v1Diff;
        float colorSlopeRed2 = (float)(c3.getRed() - c1.getRed()) / v3v1Diff;
        float colorSlopeGreen2 = (float)(c3.getGreen() - c1.getGreen()) / v3v1Diff;
        /* get starting values */
        float cBlue1 = c1.getBlue();
        float cRed1 = c1.getRed();
        float cGreen1 = c1.getGreen();
        float cBlue2 = c1.getBlue();
        float cRed2 = c1.getRed();
        float cGreen2 = c1.getGreen();
        
        /* as we will do not fill in a complete line using fillrect but instead 
         * we will loop over all pixels of a horizontal line, we need a predefined
         * direction -> choose left to right. This means that x1 must be the smaller
         * compared to x2 so slope1 must be smaller than slope2. If not we gonna
         * swap it here. Of course we have also to swap the colors of both line endpoints.
         */
        if (slope2 < slope1)
        {
            float slopeTmp = slope1;
            slope1 = slope2;
            slope2 = slopeTmp;
            
            slopeTmp = colorSlopeRed1;
            colorSlopeRed1 = colorSlopeRed2;
            colorSlopeRed2 = slopeTmp;
            
            slopeTmp = colorSlopeGreen1;
            colorSlopeGreen1 = colorSlopeGreen2;
            colorSlopeGreen2 = slopeTmp;
            
            slopeTmp = colorSlopeBlue1;
            colorSlopeBlue1 = colorSlopeBlue2;
            colorSlopeBlue2 = slopeTmp;
        }

        for (int scanlineY = v1.y; scanlineY <= v2.y; scanlineY++)
        {
            /* loop over each pixel of horizontal line */
            for (int xPos = (int)Math.ceil(x1); xPos < (int)x2; xPos++)
            {
               float t = (xPos - x1) / (x2 - x1); 
               
               int red = (int)((1 - t) * cRed1 + t * cRed2);
               int green = (int)((1 - t) * cGreen1 + t * cGreen2);
               int blue = (int)((1 - t) * cBlue1 + t * cBlue2);
                             
               g.setColor(new Color(red, green, blue));
               g.fillRect(xPos, scanlineY, 1, 1); 
            }
            /* get new x-coordinate of endpoints of horizontal line */
            x1 += slope1;
            x2 += slope2;
            /* get new color of left endpoint of horizontal line */
            cRed1 += colorSlopeRed1;
            cGreen1 += colorSlopeGreen1;
            cBlue1 += colorSlopeBlue1;
            /* get new color of right endpoint of horizontal line */
            cRed2 += colorSlopeRed2;
            cGreen2 += colorSlopeGreen2;
            cBlue2 += colorSlopeBlue2;
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
    protected void fillTopFlatTriangle(Graphics g, Vertice v1, Vertice v2, Vertice v3,
                                                   Color c1, Color c2, Color c3)
    {
        float slope1 = (float)(v3.x - v1.x) / (float)(v3.y - v1.y);
        float slope2 = (float)(v3.x - v2.x) / (float)(v3.y - v2.y);
        
        

        float x1 = v3.x;
        float x2 = v3.x + 0.5f;
        
        /* get the change of color components along edge (v3,v1) */
        float v3v1Diff = (float)(v3.y - v1.y);
        float colorSlopeBlue1 = (float)(c3.getBlue() - c1.getBlue()) / v3v1Diff;
        float colorSlopeRed1 = (float)(c3.getRed() - c1.getRed()) / v3v1Diff;
        float colorSlopeGreen1 = (float)(c3.getGreen() - c1.getGreen()) / v3v1Diff;
        /* get the change of color components along edge (v3,v2) */
        float v3v2Diff = (float)(v3.y - v2.y);
        float colorSlopeBlue2 = (float)(c3.getBlue() - c2.getBlue()) / v3v2Diff;
        float colorSlopeRed2 = (float)(c3.getRed() - c2.getRed()) / v3v2Diff;
        float colorSlopeGreen2 = (float)(c3.getGreen() - c2.getGreen()) / v3v2Diff;
        /* set starting values */
        float cBlue1 = c3.getBlue();
        float cRed1 = c3.getRed();
        float cGreen1 = c3.getGreen();
        float cBlue2 = c3.getBlue();
        float cRed2 = c3.getRed();
        float cGreen2 = c3.getGreen();
        
        /* as we will do not fill in a complete line using fillrect but instead 
         * we will loop over all pixels of a horizontal line, we need a predefined
         * direction -> choose left to right. This means that x1 must be the smaller
         * compared to x2 so slope1 must be smaller than slope2. If not we gonna
         * swap it here.
         */
        if (slope1 < slope2)
        {
            float slopeTmp = slope1;
            slope1 = slope2;
            slope2 = slopeTmp;
            
            slopeTmp = colorSlopeRed1;
            colorSlopeRed1 = colorSlopeRed2;
            colorSlopeRed2 = slopeTmp;
            
            slopeTmp = colorSlopeGreen1;
            colorSlopeGreen1 = colorSlopeGreen2;
            colorSlopeGreen2 = slopeTmp;
            
            slopeTmp = colorSlopeBlue1;
            colorSlopeBlue1 = colorSlopeBlue2;
            colorSlopeBlue2 = slopeTmp;
        }

        for (int scanlineY = v3.y; scanlineY > v1.y; scanlineY--)
        {
            /* get new x-coordinate of endpoints of horizontal line */
            for (int xPos = (int)Math.ceil(x1); xPos < (int)x2; xPos++)
            {               
               float t = (xPos - x1) / (x2 - x1); 
               
               int red = (int)((1 - t) * cRed1 + t * cRed2);
               int green = (int)((1 - t) * cGreen1 + t * cGreen2);
               int blue = (int)((1 - t) * cBlue1 + t * cBlue2);
                             
               g.setColor(new Color(red, green, blue));
               g.fillRect(xPos, scanlineY, 1, 1); 
            } 
            /* get new x-coordinate of endpoints of horizontal line */
            x1 -= slope1;
            x2 -= slope2;
            /* get new color of left endpoint of horizontal line */
            cRed1 -= colorSlopeRed1;
            cGreen1 -= colorSlopeGreen1;
            cBlue1 -= colorSlopeBlue1;
            /* get new color of right endpoint of horizontal line */
            cRed2 -= colorSlopeRed2;
            cGreen2 -= colorSlopeGreen2;
            cBlue2 -= colorSlopeBlue2;
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
            fillBottomFlatTriangle(g, vt1, vt2, vt3, colorV1, colorV2, colorV3);
        }
        /* check for trivial case of top-flat triangle */
        else if (vt1.y == vt2.y)
        {
            fillTopFlatTriangle(g, vt1, vt2, vt3, colorV1, colorV2, colorV3);
        } 
        else
        {
            /* general case - split the triangle in a top-flat and bottom-flat one */
            Vertice vTmp = new Vertice( 
                (int)(vt1.x + ((float)(vt2.y - vt1.y) / (float)(vt3.y - vt1.y)) * (vt3.x - vt1.x)), vt2.y);
            
            /* get the intermediate color at the temporary point */
            float cBlue = colorV1.getBlue() + ((float)(vt2.y - vt1.y) / (float)(vt3.y - vt1.y)) * (colorV3.getBlue() - colorV1.getBlue());
            float cRed = colorV1.getRed() + ((float)(vt2.y - vt1.y) / (float)(vt3.y - vt1.y)) * (colorV3.getRed() - colorV1.getRed());
            float cGreen = colorV1.getGreen() + ((float)(vt2.y - vt1.y) / (float)(vt3.y - vt1.y)) * (colorV3.getGreen() - colorV1.getGreen());
            Color cTmp = new Color((int)cRed, (int)cGreen, (int)cBlue);
 
            fillBottomFlatTriangle(g, vt1, vt2, vTmp, colorV1, colorV2, cTmp);
            fillTopFlatTriangle(g, vt2, vTmp, vt3, colorV2, cTmp, colorV3);
        }   
    }
    
    
}
