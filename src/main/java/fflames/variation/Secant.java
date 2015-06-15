/*
 * Secant.java
 *
 * Created on March 11, 2008, 6:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fflames.variation;

import java.awt.geom.Point2D;
/** Wariacja typu Secant
 *
 * @author victories
 */
public class Secant extends AbstractWariation {
       
    /** Creates a new instance of Secant
     * @param _coefficient wsp�czynnik wariacji
     */
    public Secant(Double _coefficient) {
        coefficient = new Double(_coefficient);
    }
    
    @Override
    public String toString() {
        return getName() + getParameters().toString();
    }
    
    public Point2D oblicz(Point2D point) {
        double r = Math.sqrt(point.getX()*point.getX() + point.getY() * point.getY());
        point.setLocation(point.getX(), 1.0/Math.cos(r*coefficient));
        return point;
    }
    
    public String getName() { return "Secant"; }
}
