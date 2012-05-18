/*
 * Bubble.java
 *
 * Created on March 11, 2008, 6:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package myfractals.wariacje;

import java.awt.geom.Point2D;
/** Wariacja typu Bubble
 *
 * @author victories
 */
public class Bubble extends AbstractWariation {
       
    /** Creates a new instance of Bubble
     * @param _coefficient wspó³czynnik wariacji
     */
    public Bubble(Double _coefficient) {
        coefficient = new Double(_coefficient);
    }
    
    @Override
    public String toString() {
        return getWariationName() + getParameters().toString();
    }
    
    public Point2D oblicz(Point2D point) {
        double x = point.getX(); double y = point.getY();
        double r = ((x*x + y*y)/4.0 + 1.0) * coefficient;
        point.setLocation(r*x, r*y);
        return point;
    }
    
    public String getWariationName() { return "Bubble"; }
}
