/*
 * Noise.java
 *
 * Created on March 11, 2008, 6:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fflames.variation;

import java.awt.geom.Point2D;
/** Wariacja typu Noise
 *
 * @author victories
 */
public class Noise extends AbstractWariation {
       
    /** Creates a new instance of Noise
     * @param _coefficient wsp??czynnik wariacji
     */
    public Noise(Double _coefficient) {
        coefficient = new Double(_coefficient);
    }
    
    @Override
    public String toString() {
        return getName() + getParameters().toString();
    }
    
    public Point2D oblicz(Point2D point) {
        double x = point.getX(); double y = point.getY();
        double u1 = Math.random();
        double u2 = Math.random();
        point.setLocation(u1*x*Math.cos(2*Math.PI*u2) * coefficient, u1*y*Math.sin(2*Math.PI*u2) * coefficient);
        return point;
    }
    
    public String getName() { return "Noise"; }
}
