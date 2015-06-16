/*
 * Perspective.java
 *
 * Created on March 10, 2008, 6:53 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fflames.generator.variation;

import java.awt.geom.Point2D;
import java.util.Vector;
/**
 *
 * @author victories
 */
public class Perspective extends AbstractWariation {
    /** Creates a new instance of Perspective
    * @param _coefficient warto?? wsp??czynnika
    */
    public Perspective(Double _coefficient) {
        coefficient = _coefficient;
    }
    
    /** Creates a new instance of Perspective
     * 
     * @param par wektor zawieraj?cy warto?ci parametr?w. Na pierwszym miejscu
     * powinien znajdowa? si?wsp??czynnik.
     */
    public Perspective(Vector<Double> par) {
        setCoefficient(par.firstElement());
        for(int i=1; i<par.size(); i++) param.add(par.get(i));
    }
    
    public Point2D oblicz(Point2D point) {
        double x = point.getX(); double y = point.getY(); 
        double p = param.get(1)/(param.get(1) - y*Math.sin(param.get(0)));
        point.setLocation(p*x*coefficient, p*y*Math.cos(param.get(0))*coefficient);
        return point;
    }
   
    public String getName() {
        return "Perspective";
    }
    
    @Override
    public int getParametersQuantity() { return 2; }
    
    @Override
    public String toString() {
        return getName() + getParameters().toString();
    }
}