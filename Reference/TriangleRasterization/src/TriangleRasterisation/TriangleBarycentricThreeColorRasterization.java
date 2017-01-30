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
public class TriangleBarycentricThreeColorRasterization extends Triangle {

    /* color components differences betweeb v2 and v1 */
    private int deltaRed12;
    private int deltaGreen12;
    private int deltaBlue12;
    
    /* color components differences betweeb v3 and v1 */
    private int deltaRed13;
    private int deltaGreen13;
    private int deltaBlue13;
    
    /**
     * Draw the triangle using barycentric coordinates using three colors.
     * @param g Graphics object
     */
    @Override
    public void rasterize(Graphics g) {
        
        /* get the bounding box of the triangle */
        int maxX = Math.max(vt1.x, Math.max(vt2.x, vt3.x));
        int minX = Math.min(vt1.x, Math.min(vt2.x, vt3.x));
        int maxY = Math.max(vt1.y, Math.max(vt2.y, vt3.y));
        int minY = Math.min(vt1.y, Math.min(vt2.y, vt3.y));
        
        /* spanning vectors of edge (v1,v2) and (v1,v3) */
        Vertice v1 = new Vertice(vt2.x - vt1.x, vt2.y - vt1.y);
        Vertice v2 = new Vertice(vt3.x - vt1.x, vt3.y - vt1.y);
        
        deltaRed12 = colorV2.getRed() - colorV1.getRed();
        deltaGreen12 = colorV2.getGreen() - colorV1.getGreen();
        deltaBlue12 = colorV2.getBlue() - colorV1.getBlue();
        
        deltaRed13 = colorV3.getRed() - colorV1.getRed();
        deltaGreen13 = colorV3.getGreen() - colorV1.getGreen();
        deltaBlue13 = colorV3.getBlue() - colorV1.getBlue();
        
        /* iterate over each pixel of bounding box and check if it's inside
         * the traingle using the barycentirc approach.
         */
        for (int x = minX; x <= maxX; x++)
        {
            for (int y = minY; y <= maxY; y++)
            {
                Vertice q = new Vertice(x - vt1.x, y - vt1.y);
                        
                float s = (float)crossProduct(q, v2) / crossProduct(v1, v2);
                float t = (float)crossProduct(v1, q) / crossProduct(v1, v2);
                
                if ( (s > 0) && (t > 0) && (s + t <= 1))
                {
                    Color c = getBarycentricColor(s, t);
                    g.setColor(c);
                    g.fillRect(x, y, 1, 1);
                }
            }
        }
    }
    
    /**
     * Calculcates the new color out of the three edge colors using the intermediate s and t values.
     * Precondition: may only be called if the point related to s and t is inside the triangle
     * which means that s+t < 1.
     * @param s proportional value between v1 and v2
     * @param t proportional value between v1 and v3
     * @return the new color. May throw IllegalArgumentException if s + t > 1.
     */
    private Color getBarycentricColor(float s, float t) {
        int resRed = colorV1.getRed() + (int)(s * deltaRed12) + (int)(t * deltaRed13);
        int resGreen = colorV1.getGreen() + (int)(s * deltaGreen12) + (int)(t * deltaGreen13);
        int resBlue = colorV1.getBlue() + (int)(s * deltaBlue12) + (int)(t * deltaBlue13);
                
        return new Color(resRed, resGreen, resBlue);
    }
    
}
