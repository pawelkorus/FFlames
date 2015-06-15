/*
 * Power.java
 *
 * Created on March 11, 2008, 6:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fflames.variation;

import java.awt.geom.Point2D;
/** Wariacja typu Power
 *
 * @author victories
 */
public class Power extends AbstractWariation {
       
    /** Creates a new instance of Power
     * @param _coefficient wsp�czynnik wariacji
     */
    public Power(Double _coefficient) {
        coefficient = new Double(_coefficient);
    }
    
    @Override
    public String toString() {
        return getName() + getParameters().toString();
    }
    
    public Point2D oblicz(Point2D point) {
        double x = point.getX(); double y = point.getY();
        double r = Math.sqrt(x*x + y*y);
        double t = Math.atan2(x,y);
        double pot = Math.pow(r, Math.sin(t));
        point.setLocation(pot*Math.cos(t)*coefficient, pot*Math.sin(t)*coefficient);
        return point;
    }
    
    public String getName() { return "Power"; }
}
