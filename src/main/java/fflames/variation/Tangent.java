/*
 * Tangent.java
 *
 * Created on March 11, 2008, 6:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fflames.variation;

import java.awt.geom.Point2D;
/** Wariacja typu Tangent
 *
 * @author victories
 */
public class Tangent extends AbstractWariation {
       
    /** Creates a new instance of Tangent
     * @param _coefficient wsp�czynnik wariacji
     */
    public Tangent(Double _coefficient) {
        coefficient = new Double(_coefficient);
    }
    
    @Override
    public String toString() {
        return getName() + getParameters().toString();
    }
    
    public Point2D oblicz(Point2D point) {
        point.setLocation(coefficient*Math.sin(point.getX())/Math.cos(point.getY()),
                coefficient*Math.sin(point.getY())/Math.cos(point.getX()));
        return point;
    }
    
    public String getName() { return "Tangent"; }
}
