/*
 * Cosine.java
 *
 * Created on March 11, 2008, 6:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fflames.generator.variation;

import java.awt.geom.Point2D;
/** Wariacja typu Cosine
 *
 * @author victories
 */
public class Cosine extends AbstractWariation {
       
    /** Creates a new instance of Cosine
     * @param _coefficient wsp�czynnik wariacji
     */
    public Cosine(Double _coefficient) {
        coefficient = new Double(_coefficient);
    }
    
    @Override
    public String toString() {
        return getName() + getParameters().toString();
    }
    
    public Point2D oblicz(Point2D point) {
        double x = point.getX(); double y = point.getY();
        point.setLocation(Math.cos(Math.PI*x)*Math.cosh(y) * coefficient, 
                -Math.sin(Math.PI*x)*Math.sinh(y)*coefficient);
        return point;
    }
    
    public String getName() { return "Cosine"; }
}
