/*
 * Cylinder.java
 *
 * Created on March 11, 2008, 6:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fflames.generator.variation;

import java.awt.geom.Point2D;
/** Wariacja typu Cylinder
 *
 * @author victories
 */
public class Cylinder extends AbstractWariation {
       
    /** Creates a new instance of Cylinder
     * @param _coefficient wsp�czynnik wariacji
     */
    public Cylinder(Double _coefficient) {
        coefficient = new Double(_coefficient);
    }
    
    @Override
    public String toString() {
        return getName() + getParameters().toString();
    }
    
    public Point2D oblicz(Point2D point) {
        double x = point.getX(); double y = point.getY();
        point.setLocation(Math.sin(x) * coefficient, y * coefficient);
        return point;
    }
    
    public String getName() { return "Cylinder"; }
}
