/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fflames.generator.variation;

import java.awt.geom.Point2D;

/**
 * Wariacja typu Handkerchief
 * @author Victories
 */
public class Handkerchief extends AbstractWariation {

    /**
     * Konstruktor obiektu klasy Handkerchief
     * @param _coefficient warto�� wsp�czynnika
     */
    public Handkerchief(Double _coefficient) {
        coefficient = new Double(_coefficient);
    }
    
    public Point2D oblicz(Point2D point) {
        double x = point.getX(); double y = point.getY();
        double r = Math.sqrt(x*x + y*y);
        double theta = Math.atan2(x, y);
        point.setLocation(r*Math.sin(theta + r) * coefficient, 
                r*Math.cos(theta - r) * coefficient);
        return point;
    }

    public String getName() {
        return "Handkerchief";
    }
    
    @Override
    public String toString() {
        return getName() + getParameters().toString();
    }
}
