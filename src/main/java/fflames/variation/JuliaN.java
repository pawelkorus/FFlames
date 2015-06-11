/*
 * JuliaN.java
 *
 * Created on March 10, 2008, 6:53 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fflames.variation;

import java.awt.geom.Point2D;
import java.util.Vector;
/**
 *
 * @author victories
 */
public class JuliaN extends AbstractWariation {
    /** Creates a new instance of JuliaN
    * @param _coefficient warto?? wsp??czynnika
    */
    public JuliaN(Double _coefficient) {
        coefficient = _coefficient;
    }
    
    /** Creates a new instance of JuliaN
     * 
     * @param par wektor zawieraj?cy warto?ci parametr?w. Na pierwszym miejscu
     * powinien znajdowa? si?wsp??czynnik.
     */
    public JuliaN(Vector<Double> par) {
        setCoefficient(par.firstElement());
        for(int i=1; i<par.size(); i++) param.add(par.get(i));
    }
    
    public Point2D oblicz(Point2D point) {
        double x = point.getX(); double y = point.getY();
        double phi = Math.atan2(y, x);
        double r = Math.sqrt(x*x + y*y);
        double t = (phi+2*Math.PI*Math.abs(param.get(0))*Math.random())/param.get(0);
        point.setLocation(Math.pow(r, param.get(1)/param.get(0))*Math.cos(t) * coefficient,
                            Math.pow(r, param.get(1)/param.get(0))*Math.sin(t) * coefficient);
        return point;
    }
   
    public String getWariationName() {
        return "JuliaN";
    }
    
    @Override
    public int getParametersQuantity() { return 2; }
    
    @Override
    public String toString() {
        return getWariationName() + getParameters().toString();
    }
}