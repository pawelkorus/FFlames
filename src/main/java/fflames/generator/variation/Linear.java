/*
 * Linear.java
 *
 * Created on March 11, 2008, 6:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fflames.generator.variation;

import java.awt.geom.Point2D;
/** Wariacja typu Linear
 *
 * @author victories
 */
public class Linear extends AbstractWariation {
       
    /** Creates a new instance of Linear
     * @param _coefficient wsp�czynnik wariacji
     */
    public Linear(Double _coefficient) {
        coefficient = new Double(_coefficient);
    }
    
    @Override
    public String toString() {
        return getName() + getParameters().toString();
    }
    
    public Point2D oblicz(Point2D point) {
        point.setLocation(point.getX()*coefficient, point.getY()*coefficient);
        return point;
    }
    
    public String getName() { return "Linear"; }
}
