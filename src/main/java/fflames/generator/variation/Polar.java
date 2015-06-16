/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fflames.generator.variation;

import java.awt.geom.Point2D;

/** Wariacja typu Polar
 *
 * @author Victories
 */
public class Polar extends AbstractWariation {

    /**
     * Konstruktor obiektu klasy Polar
     * @param _coefficient warto�� wsp�czynnika
     */
    public Polar(Double _coefficient) {
        coefficient = new Double(_coefficient);
    }
    
    
    public Point2D oblicz(Point2D point) {
        double x = point.getX(); double y = point.getY();
        double r = Math.sqrt(x*x + y*y);
        point.setLocation(Math.atan2(x, y)/Math.PI * coefficient, 
                r-1.0 * coefficient);
        return point;
    }

    public String getName() {
        return "Polar";
    }

    @Override
    public String toString() {
        return getName() + getParameters().toString();
    }
}
