/*
 * Hyperbolic.java
 *
 * Created on March 11, 2008, 6:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fflames.variation;

import java.awt.geom.Point2D;
/** Wariacja typu Hyperbolic
 *
 * @author victories
 */
public class Hyperbolic extends AbstractWariation {
       
    /** Creates a new instance of Hyperbolic
     * @param _coefficient wsp�czynnik wariacji
     */
    public Hyperbolic(Double _coefficient) {
        coefficient = new Double(_coefficient);
    }
    
    @Override
    public String toString() {
        return getName() + getParameters().toString();
    }
    
    public Point2D oblicz(Point2D point) {
        double x = point.getX(); double y = point.getY();
        double r = Math.sqrt(x*x + y*y) + 0.000001;
        double t = Math.atan2(x, y);
        point.setLocation(Math.sin(t)/r * coefficient, 
                r*Math.cos(t) * coefficient);
        return point;
    }
    
    public String getName() { return "Hyperbolic"; }
}