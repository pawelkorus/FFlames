/*
 * Spherical.java
 *
 * Created on March 10, 2008, 6:30 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fflames.generator.variation;

import java.awt.geom.Point2D;
/**
 *
 * @author victories
 */
public class Spherical extends AbstractWariation {
    /** Creates a new instance of Spherical
     * @param _coefficient warto�� wsp�czynnika
     */
    public Spherical(Double _coefficient) {
        coefficient = new Double(_coefficient);
    }
    
    @Override
    public String toString() { 
        return getName() + getParameters().toString(); 
    }
    
    public Point2D oblicz(Point2D point) {
        double x = point.getX(); double y = point.getY();
        // Zmiana wprowadzona za wiki electicsheep
        double r = 1/(x*x + y*y + 0.000001);
        point.setLocation(x*r*coefficient, y*r*coefficient);
        return point;
    }
    
        
    public String getName() { return "Spherical"; }
}
