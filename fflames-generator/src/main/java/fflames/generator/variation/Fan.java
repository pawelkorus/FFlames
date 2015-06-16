/*
 * Fan.java
 *
 * Created on March 11, 2008, 6:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fflames.generator.variation;

import java.awt.geom.Point2D;
/** Wariacja typu Fan
 *
 * @author victories
 */
public class Fan extends AbstractWariation {
       
    /** Creates a new instance of Fan
     * @param _coefficient wsp�czynnik wariacji
     */
    public Fan(Double _coefficient) {
        coefficient = new Double(_coefficient);
    }
    
    @Override
    public String toString() {
        return getName() + getParameters().toString();
    }
    
    public Point2D oblicz(Point2D point) {
        double x = point.getX(); double y = point.getY();
        double r = Math.sqrt(x*x+y*y);
        double theta = Math.atan(x/y);
        double c = param.get(4)*param.get(4);
        double f = param.get(5);
        double t = Math.PI*c;
        
        if(Math.IEEEremainder(theta+f, t) > t/2.0)
            point.setLocation(r*Math.cos(theta - t/2.0) * coefficient, 
                    r*Math.sin(theta - t/2.0) * coefficient);
        else
            point.setLocation(r*Math.cos(theta + t/2.0) * coefficient, 
                    r*Math.sin(theta + t/2.0)*coefficient);
        
        return point;
    }
    
    public String getName() { return "Fan"; }
    
    @Override
    public boolean isDependent() {
        return true;
    }
}
