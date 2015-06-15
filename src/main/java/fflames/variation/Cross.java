/*
 * Cross.java
 *
 * Created on March 11, 2008, 6:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fflames.variation;

import java.awt.geom.Point2D;
/** Wariacja typu Cross
 *
 * @author victories
 */
public class Cross extends AbstractWariation {
       
    /** Creates a new instance of Cross
     * @param _coefficient wsp�czynnik wariacji
     */
    public Cross(Double _coefficient) {
        coefficient = new Double(_coefficient);
    }
    
    @Override
    public String toString() {
        return getName() + getParameters().toString();
    }
    
    public Point2D oblicz(Point2D point) {
        double x = point.getX(); double y = point.getY();
        double r = (x*x-y*y);
        double wsp = coefficient*Math.sqrt(1.0/(r*r + 0.000001));
        point.setLocation(wsp * x, wsp * y);
        return point;
    }
    
    public String getName() { return "Cross"; }
}
