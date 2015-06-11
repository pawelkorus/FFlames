/*
 * Blur.java
 *
 * Created on March 11, 2008, 6:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fflames.variation;

import java.awt.geom.Point2D;
/** Wariacja typu Blur
 *
 * @author victories
 */
public class Blur extends AbstractWariation {
       
    /** Creates a new instance of Blur
     * @param _coefficient wsp�czynnik wariacji
     */
    public Blur(Double _coefficient) {
        coefficient = new Double(_coefficient);
    }
    
    @Override
    public String toString() {
        return getWariationName() + getParameters().toString();
    }
    
    public Point2D oblicz(Point2D point) {
        double psi1 = Math.random() * 2 * Math.PI;
        double psi2 = Math.random();
        point.setLocation(psi2 * Math.cos(psi1) * coefficient, 
                psi2 * Math.sin(psi1) * coefficient);
        return point;
    }
    
    public String getWariationName() { return "Blur"; }
}
