/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fflames.generator.variation;

import java.awt.geom.Point2D;

/**
 * Wariacja typu Swirl
 * @author Victories
 */
public class Swirl extends AbstractWariation {

    /** Tworzy nowy obiekt klasy Swirl 
     * 
     * @param _coefficient warto�� wsp�czynnika
     */
    public Swirl(Double _coefficient) {
        coefficient = new Double(_coefficient);
    }
    
    
    public Point2D oblicz(Point2D point) {
        double x = point.getX(); double y = point.getY();
        // Obliczam tylko kwadraty bo w przeksztalceniu potrzebny jest i tak
        // kwadrat z r. Nie ma sensu liczy� pierwiastka.
        Double r = new Double(x*x + y*y);
        point.setLocation( (x*Math.sin(r) - y*Math.cos(r)) * coefficient, 
                (x*Math.cos(r)+y*Math.sin(r)) * coefficient);
        return point;
    }

    public String getName() {
        return "Swirl";
    }

    @Override
    public String toString() {
        return getName() + getParameters().toString();
    }
}
