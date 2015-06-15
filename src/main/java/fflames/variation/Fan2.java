/*
 * Fan2.java
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
public class Fan2 extends AbstractWariation {
    /** Creates a new instance of Fan2
    * @param _coefficient warto�� wsp�czynnika
    */
    public Fan2(Double _coefficient) {
        coefficient = _coefficient;
    }
    
    /** Creates a new instance of Fan2
     * 
     * @param par wektor zawieraj�cy warto�ci parametr�w. Na pierwszym miejscu
     * powinien znajdowa� si�wsp�czynnik.
     */
    public Fan2(Vector<Double> par) {
        setCoefficient(par.firstElement());
        for(int i=1; i<par.size(); i++) param.add(par.get(i));
    }
    
    public Point2D oblicz(Point2D point) {
        double x = point.getX(); double y = point.getY();
        double r = Math.sqrt(x*x + y*y) * coefficient;
        double p1 = Math.PI*(param.get(0)*param.get(0) + 0.000001);
        double p2 = param.get(1);
        double theta = Math.atan2(x, y);
        double t = theta + p2 - p1*Math.floor(theta+p2/p1);
        if(t > p1/2.0)
            theta -= p1/2.0;
        else
            theta += p1/2.0;
        
        point.setLocation(r*Math.sin(theta), r*Math.cos(theta));
        return point;
    }
   
    public String getName() {
        return "Fan2";
    }
    
    @Override
    public int getParametersQuantity() { return 2; }
    
    @Override
    public String toString() {
        return getName() + getParameters().toString();
    }
}