/*
 * Arch.java
 *
 * Created on March 10, 2008, 6:53 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package myfractals.wariacje;

import java.awt.geom.Point2D;
/**
 *
 * @author victories
 */
public class Arch extends AbstractWariation {
    /** Creates a new instance of Arch
    * @param _coefficient wartoœæ wspó³czynnika
    */
    public Arch(Double _coefficient) {
        coefficient = _coefficient;
    }
     
    public Point2D oblicz(Point2D point) {
        double ang = Math.random() * Math.PI * coefficient;
        point.setLocation(coefficient*Math.sin(ang), 
                coefficient * Math.sin(ang)*Math.sin(ang)/Math.cos(ang));
        return point;
    }
   
    public String getWariationName() {
        return "Arch";
    }
    
    @Override
    public String toString() {
        return getWariationName() + getParameters().toString();
    }
}