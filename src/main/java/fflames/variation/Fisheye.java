/*
 * Fisheye.java
 *
 * Created on March 11, 2008, 6:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fflames.variation;

import java.awt.geom.Point2D;
/** Wariacja typu Fisheye
 *
 * @author victories
 */
public class Fisheye extends AbstractWariation {
       
    /** Creates a new instance of Fisheye
     * @param _coefficient wsp�czynnik wariacji
     */
    public Fisheye(Double _coefficient) {
        coefficient = new Double(_coefficient);
    }
    
    @Override
    public String toString() {
        return getName() + getParameters().toString();
    }
    
    public Point2D oblicz(Point2D point) {
        double x = point.getX(); double y = point.getY();
        double r = Math.sqrt(x*x + y*y);
        double a = Math.atan2(x, y);
        point.setLocation(2.0*r/(r+1.0)* Math.cos(a) * coefficient, 
                2.0*r/(r+1.0) * Math.sin(a) * coefficient);
        return point;
    }
    
    public String getName() { return "Fisheye"; }
}
