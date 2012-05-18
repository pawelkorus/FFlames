/*
 * Rings.java
 *
 * Created on March 11, 2008, 6:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package myfractals.wariacje;

import java.awt.geom.Point2D;
/** Wariacja typu Rings
 *
 * @author victories
 */
public class Rings extends AbstractWariation {
       
    /** Creates a new instance of Rings
     * @param _coefficient wsp�czynnik wariacji
     */
    public Rings(Double _coefficient) {
        coefficient = new Double(_coefficient);
    }
    
    @Override
    public String toString() {
        return getWariationName() + getParameters().toString();
    }
    
    public Point2D oblicz(Point2D point) {
        double x = point.getX(); double y = point.getY();
        double r = Math.sqrt(x*x+y*y);
        double t = Math.atan(x/y);
        double c = param.get(4)*param.get(4) + 0.000001;
        double mn =  (Math.IEEEremainder(r+c, 2*c) - c + r*(1-c)) * coefficient;
        point.setLocation(mn*Math.cos(t), mn*Math.sin(t));
        return point;
    }
    
    public String getWariationName() { return "Rings"; }
    
    @Override
    public boolean isDependent() {
        return true;
    }
}
