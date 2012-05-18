/*
 * Rays.java
 *
 * Created on March 11, 2008, 6:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package myfractals.wariacje;

import java.awt.geom.Point2D;
/** Wariacja typu Rays
 *
 * @author victories
 */
public class Rays extends AbstractWariation {
       
    /** Creates a new instance of Rays
     * @param _coefficient wspó³czynnik wariacji
     */
    public Rays(Double _coefficient) {
        coefficient = new Double(_coefficient);
    }
    
    @Override
    public String toString() {
        return getWariationName() + getParameters().toString();
    }
    
    public Point2D oblicz(Point2D point) {
        double x = point.getX(); double y = point.getY();
        double ang = Math.random() * Math.PI * coefficient;
        double r = coefficient/(x*x + y*y + 0.000001);
        double tanr = coefficient * Math.tan(ang) * r;
        point.setLocation(tanr*Math.cos(x), tanr*Math.sin(y));        
        return point;
    }
    
    public String getWariationName() { return "Rays"; }
}
