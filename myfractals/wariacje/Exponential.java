/*
 * Exponential.java
 *
 * Created on March 11, 2008, 6:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package myfractals.wariacje;

import java.awt.geom.Point2D;
/** Wariacja typu Exponential
 *
 * @author victories
 */
public class Exponential extends AbstractWariation {
       
    /** Creates a new instance of Exponential
     * @param _coefficient wspó³czynnik wariacji
     */
    public Exponential(Double _coefficient) {
        coefficient = new Double(_coefficient);
    }
    
    @Override
    public String toString() {
        return getWariationName() + getParameters().toString();
    }
    
    public Point2D oblicz(Point2D point) {
        double x = point.getX(); double y = point.getY();
        point.setLocation(Math.exp(x-1.0)*Math.cos(Math.PI*y)*coefficient, 
                Math.exp(x-1.0)*Math.sin(Math.PI*y)*coefficient);
        return point;
    }
    
    public String getWariationName() { return "Exponential"; }
}
