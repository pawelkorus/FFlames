/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fflames.generator.variation;

import java.awt.geom.Point2D;

/** Wariacja typu Horseshoe
 *
 * @author Victories
 */
public class Horseshoe extends AbstractWariation {

    /** Zwraca nowy obiekt tej klasy
     * 
     * @param _coefficient warto�� wsp�czynnika
     */
    public Horseshoe(Double _coefficient) {
        coefficient = new Double(_coefficient);
    }
    
    public Point2D oblicz(Point2D point) {
        double x = point.getX(); double y = point.getY();
        // Zmiana za wiki electricsheep
        double r = 1/(Math.sqrt(x*x + y*y) + 0.000001);
        point.setLocation(r*(x-y)*(x+y)*coefficient, 
                r*2*x*y*coefficient);
        return point;
    }

    public String getName() {
        return "Horseshoe";
    }

    @Override
    public String toString() {
        return getName() + getParameters().toString();
    }
}
