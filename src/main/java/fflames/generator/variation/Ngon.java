/*
 * Ngon.java
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
public class Ngon extends AbstractWariation {
    /** Creates a new instance of Ngon
    * @param _coefficient warto�� wsp�czynnika
    */
    public Ngon(Double _coefficient) {
        coefficient = _coefficient;
    }
    
    /** Creates a new instance of Ngon
     * 
     * @param par wektor zawieraj�cy warto�ci parametr�w. Na pierwszym miejscu
     * powinien znajdowa� si�wsp�czynnik.
     */
    public Ngon(Vector<Double> par) {
        setCoefficient(par.firstElement());
        for(int i=1; i<par.size(); i++) param.add(par.get(i));
    }
    
    public Point2D oblicz(Point2D point) {
        double x = point.getX(); double y = point.getY();
        double r_factor = Math.pow(x*x+y*y, param.get(0)/2.0);
        double theta = Math.atan2(y, x);
        double b = 2*Math.PI/(param.get(1)+0.000001);
        double phi = theta - (b*Math.floor(theta/b));
        if(phi > b/2.0)
            phi -= b;
        double amp = param.get(2) * ( 1.0/(Math.cos(phi)+0.000001)-1.0) + param.get(3);
        amp = amp/(r_factor+0.000001);
        point.setLocation(coefficient*x*amp,
                coefficient*y*amp);
        return point;
    }
   
    public String getName() {
        return "Ngon";
    }
    
    @Override
    public int getParametersQuantity() { return 4; }
    
    @Override
    public String toString() {
        return getName() + getParameters().toString();
    }
}