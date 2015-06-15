/*
 * Eyefish.java
 *
 * Created on March 11, 2008, 6:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fflames.variation;

import java.awt.geom.Point2D;
/** Wariacja typu Eyefish
 *
 * @author victories
 */
public class Eyefish extends AbstractWariation {
       
    /** Creates a new instance of Eyefish
     * @param _coefficient wsp�czynnik wariacji
     */
    public Eyefish(Double _coefficient) {
        coefficient = new Double(_coefficient);
    }
    
    @Override
    public String toString() {
        return getName() + getParameters().toString();
    }
    
    public Point2D oblicz(Point2D point) {
        double x = point.getX(); double y = point.getY();
        double r = Math.sqrt(x*x + y*y);
        point.setLocation(2.0/(r+1.0)*x*coefficient, 2.0/(r+1.0)*y*coefficient);
        return point;
    }
    
    public String getName() { return "Eyefish"; }
}
