/*
 * JuliaScope.java
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
public class JuliaScope extends AbstractWariation {
    /** Creates a new instance of JuliaScope
    * @param _coefficient warto?? wsp??czynnika
    */
    public JuliaScope(Double _coefficient) {
        coefficient = _coefficient;
    }
    
    /** Creates a new instance of JuliaScope
     * 
     * @param par wektor zawieraj?cy warto?ci parametr?w. Na pierwszym miejscu
     * powinien znajdowa? si?wsp??czynnik.
     */
    public JuliaScope(Vector<Double> par) {
        setCoefficient(par.firstElement());
        for(int i=1; i<par.size(); i++) param.add(par.get(i));
    }
    
    public Point2D oblicz(Point2D point) {
        double x = point.getX(); double y = point.getY();
        double psi = Math.round(Math.random() * 10.0);
        double rand = Math.random()*2-1.0;
        double phi = Math.atan2(y, x);
        double t = (rand*phi+2.0*Math.PI*Math.abs(param.get(0))*psi)/param.get(1);
        double r = Math.sqrt(x*x+y*y);
        point.setLocation(Math.pow(r, param.get(0)/param.get(1))*Math.cos(t)*coefficient, 
                Math.pow(r, param.get(0)/param.get(1))*Math.sin(t)*coefficient);
        return point;
    }
   
    public String getName() {
        return "JuliaScope";
    }
    
    @Override
    public int getParametersQuantity() { return 2; }
    
    @Override
    public String toString() {
        return getName() + getParameters().toString();
    }
}