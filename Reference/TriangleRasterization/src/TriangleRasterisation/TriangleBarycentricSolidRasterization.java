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
public class TriangleBarycentricSolidRasterization extends Triangle {

    /**
     * Draw the triangle using barycentric coordinates using one solid color.
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
        
        g.setColor(solidColor);
        
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
                
                if ( (s >= 0) && (t >= 0) && (s + t <= 1))
                {
                    g.fillRect(x, y, 1, 1);
                }
            }
        }
    }
    
}
