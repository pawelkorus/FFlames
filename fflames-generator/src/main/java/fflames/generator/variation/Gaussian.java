/*
 * Gaussian.java
 *
 * Created on March 11, 2008, 6:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fflames.generator.variation;

import java.awt.geom.Point2D;
/** Wariacja typu Gaussian
 *
 * @author victories
 */
public class Gaussian extends AbstractWariation {
       
    /** Creates a new instance of Gaussian
     * @param _coefficient wsp�czynnik wariacji
     */
    public Gaussian(Double _coefficient) {
        coefficient = new Double(_coefficient);
    }
    
    @Override
    public String toString() {
        return getName() + getParameters().toString();
    }
    
    public Point2D oblicz(Point2D point) {
        double sumpsi = 0.0;
        for(int i=0; i<4; i++) { sumpsi += Math.random(); }
        sumpsi -= 2.0; sumpsi *= coefficient;
        double angle = Math.random() * 2 * Math.PI;
        double sina = Math.sin(angle);
        double cosa = Math.cos(angle);
        point.setLocation(sumpsi*cosa, sumpsi*sina);
        return point;
    }
    
    public String getName() { return "Gaussian"; }
}
