/*
 * Diamond.java
 *
 * Created on March 11, 2008, 6:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fflames.variation;

import java.awt.geom.Point2D;
/** Wariacja typu Diamond
 *
 * @author victories
 */
public class Diamond extends AbstractWariation {
       
    /** Creates a new instance of Diamond
     * @param _coefficient wsp�czynnik wariacji
     */
    public Diamond(Double _coefficient) {
        coefficient = new Double(_coefficient);
    }
    
    @Override
    public String toString() {
        return getWariationName() + getParameters().toString();
    }
    
    public Point2D oblicz(Point2D point) {
        double x = point.getX(); double y = point.getY();
        double r = Math.sqrt(x*x + y*y);
        double t = Math.atan2(x,y);
        point.setLocation(Math.sin(t)*Math.cos(r) * coefficient, 
                Math.cos(t)*Math.sin(r) * coefficient);
        return point;
    }
    
    public String getWariationName() { return "Diamond"; }
}
