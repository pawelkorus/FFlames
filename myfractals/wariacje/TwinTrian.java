/*
 * TwinTrian.java
 *
 * Created on March 11, 2008, 6:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package myfractals.wariacje;

import java.awt.geom.Point2D;
/** Wariacja typu TwinTrian
 *
 * @author victories
 */
public class TwinTrian extends AbstractWariation {
       
    /** Creates a new instance of TwinTrian
     * @param _coefficient wspó³czynnik wariacji
     */
    public TwinTrian(Double _coefficient) {
        coefficient = new Double(_coefficient);
    }
    
    @Override
    public String toString() {
        return getWariationName() + getParameters().toString();
    }
    
    public Point2D oblicz(Point2D point) {
        double x = point.getX(); double y = point.getY();
        double r = Math.sqrt(x*x+y*y);
        double sin = coefficient*Math.random()*r;
        double t = Math.log10(sin*sin) + Math.cos(coefficient*Math.random()*r);
        point.setLocation(x*t*coefficient, coefficient*x*(t - Math.PI*sin));
        return point;
    }
    
    public String getWariationName() { return "TwinTrian"; }
}
