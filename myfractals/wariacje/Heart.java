/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package myfractals.wariacje;

import java.awt.geom.Point2D;

/**
 * Wariacja typu Heart
 * @author Victories
 */
public class Heart extends AbstractWariation {

    /**
     * Konstruktor obiektu klasy Heart
     * @param _coefficient warto�� wsp�czynnika
     */ 
    public Heart(Double _coefficient) {
        coefficient = new Double(_coefficient);
    }

    public Point2D oblicz(Point2D point) {
        double x = point.getX(); double y = point.getY();
        double r = Math.sqrt(x*x + y*y);
        double theta = Math.atan2(x, y);
        point.setLocation(r * Math.sin(theta*r) * coefficient, 
                (-r) * Math.cos(theta*r) * coefficient);
        return point;
    }

    public String getWariationName() {
        return "Heart";
    }

    @Override
    public String toString() {
        return getWariationName() + getParameters().toString();
    }
}
