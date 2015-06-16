/*
 * Disc.java
 *
 * Created on March 11, 2008, 6:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fflames.generator.variation;

import java.awt.geom.Point2D;
/** Wariacja typu Disc
 *
 * @author Victories
 */
public class Disc extends AbstractWariation {
    
    /** Creates a new instance of Disc
     * @param _coefficient wsp�czynnik wariacji
     */
    public Disc(Double _coefficient) {
        coefficient = new Double(_coefficient);
    }
    
    @Override
    public String toString() {
        return getName() + getParameters().toString();
    }
    
    public Point2D oblicz(Point2D point) {
        double x = point.getX(); double y = point.getY();
        //Zmiany za wiki electricsheep
        double r = Math.PI * Math.sqrt(x*x + y*y);
        double theta = Math.atan2(Math.PI*x, Math.PI*y)/Math.PI;
        point.setLocation(Math.sin(r)*theta*coefficient, 
                Math.cos(r) * theta * coefficient);
        return point;
    }
    
    public String getName() {
        return "Disc";
    }
}
