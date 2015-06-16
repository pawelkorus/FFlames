/*
 * Sinusoidal.java
 *
 * Created on March 10, 2008, 6:11 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fflames.generator.variation;

import java.awt.geom.Point2D;
/** Wariacja typu Sinusoidal
 *
 * @author victories
 */
public class Sinusoidal extends AbstractWariation {
    /** Creates a new instance of Sinusoidal
     * @param _coefficient warto�� wsp�czynnika
     */
    public Sinusoidal(double _coefficient) {
        coefficient = new Double(_coefficient);
    }
    
    @Override
    public String toString() { 
        return getName() + getParameters().toString(); 
    }
    
    public Point2D oblicz(Point2D punkt) {
        punkt.setLocation(Math.sin(punkt.getX()) * coefficient, Math.sin(punkt.getY()) * coefficient);
        return punkt;
    }
    
    public String getName() { return "Sinusoidal"; }
}
