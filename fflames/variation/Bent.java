/*
 * Bent.java
 *
 * Created on March 11, 2008, 6:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fflames.variation;

import java.awt.geom.Point2D;
/** Wariacja typu Bent
 *
 * @author victories
 */
public class Bent extends AbstractWariation {
       
    /** Creates a new instance of Bent
     * @param _coefficient wsp�czynnik wariacji
     */
    public Bent(Double _coefficient) {
        coefficient = new Double(_coefficient);
    }
    
    @Override
    public String toString() {
        return getWariationName() + getParameters().toString();
    }
    
    public Point2D oblicz(Point2D point) {
        double x = point.getX(); double y = point.getY();
        if(x < 0) x *= 2.0;
        if(y < 0) y /= 2.0;
        point.setLocation(x*coefficient, y*coefficient);
        return point;
    }
    
    public String getWariationName() { 
        return "Bent"; 
    }
}
