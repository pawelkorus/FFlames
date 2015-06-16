/*
 * Rings2.java
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
public class Rings2 extends AbstractWariation {
    /** Creates a new instance of Rings2
    * @param _coefficient warto�� wsp�czynnika
    */
    public Rings2(Double _coefficient) {
        coefficient = _coefficient;
    }
    
    /** Creates a new instance of Rings2
     * 
     * @param par wektor zawieraj�cy warto�ci parametr�w. Na pierwszym miejscu
     * powinien znajdowa� si�wsp�czynnik.
     */
    public Rings2(Vector<Double> par) {
        setCoefficient(par.firstElement());
        for(int i=1; i<par.size(); i++) param.add(par.get(i));
    }
    
    public Point2D oblicz(Point2D point) {
        double x = point.getX(); double y = point.getY();
        double r = Math.sqrt(x*x + y*y);
        double p = param.get(0) * param.get(0) + 0.000001;
        double theta = Math.atan2(x, y);
        double t = r - 2*p*Math.floor((r+p)/2*p) + r*(1-p);
        point.setLocation(t*Math.sin(theta)*coefficient, t*Math.cos(theta)*coefficient);
        return point;
    }
        
    public String getName() {
        return "Rings2";
    }
    
    @Override
    public int getParametersQuantity() { return 1; }
    
    @Override
    public String toString() {
        return getName() + getParameters().toString();
    }
}