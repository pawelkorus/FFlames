/*
 * Popcorn.java
 *
 * Created on March 11, 2008, 6:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fflames.generator.variation;

import java.awt.geom.Point2D;
/** Wariacja typu Popcorn
 *
 * @author victories
 */
public class Popcorn extends AbstractWariation {
       
    /** Creates a new instance of Popcorn
     * @param _coefficient wsp�czynnik wariacji
     */
    public Popcorn(Double _coefficient) {
        coefficient = new Double(_coefficient);
    }
    
    @Override
    public String toString() {
        return getName() + getParameters().firstElement().toString();
    }
    
    public Point2D oblicz(Point2D point) {
        double x = point.getX(); double y = point.getY();
        point.setLocation( (x + param.get(4)*Math.sin(Math.tan(3*y))) * coefficient, 
                (y + param.get(5)*Math.sin(Math.tan(3*x)))*coefficient );
        return point;
    }
    
    public String getName() { return "Popcorn"; }
    
    @Override
    public boolean isDependent() {
        return true;
    }
}