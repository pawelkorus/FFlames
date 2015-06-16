/*
 * Blade.java
 *
 * Created on March 11, 2008, 6:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fflames.generator.variation;

import java.awt.geom.Point2D;
/** Wariacja typu Blade
 *
 * @author victories
 */
public class Blade extends AbstractWariation {
       
    /** Creates a new instance of Blade
     * @param _coefficient wsp�czynnik wariacji
     */
    public Blade(Double _coefficient) {
        coefficient = new Double(_coefficient);
    }
    
    @Override
    public String toString() {
        return getName() + getParameters().toString();
    }
    
    public Point2D oblicz(Point2D point) {
        double x = point.getX(); double y = point.getY();
        double r = Math.random()*coefficient*Math.sqrt(x*x+y*y);
        point.setLocation(coefficient*x*(Math.cos(r) + Math.sin(r)),
                x*1.0/Math.cos(r));
        return point;
    }
    
    public String getName() { return "Blade"; }
}
