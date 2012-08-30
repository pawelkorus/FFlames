/*
 * Square.java
 *
 * Created on March 11, 2008, 6:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fflames.variation;

import java.awt.geom.Point2D;
/** Wariacja typu Square
 *
 * @author victories
 */
public class Square extends AbstractWariation {
       
    /** Creates a new instance of Square
     * @param _coefficient wsp�czynnik wariacji
     */
    public Square(Double _coefficient) {
        coefficient = new Double(_coefficient);
    }
    
    @Override
    public String toString() {
        return getWariationName() + getParameters().toString();
    }
    
    public Point2D oblicz(Point2D point) {
        point.setLocation((Math.random()-0.5)*coefficient, (Math.random()-0.5)*coefficient);
        return point;
    }
    
    public String getWariationName() { return "Square"; }
}
