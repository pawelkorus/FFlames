/*
 * Rectangles.java
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
public class Rectangles extends AbstractWariation {
    /** Creates a new instance of Rectangles
    * @param _coefficient warto�� wsp�czynnika
    */
    public Rectangles(Double _coefficient) {
        coefficient = _coefficient;
    }
    
    /** Creates a new instance of Rectangles
     * 
     * @param par wektor zawieraj�cy warto�ci parametr�w. Na pierwszym miejscu
     * powinien znajdowa� si�wsp�czynnik.
     */
    public Rectangles(Vector<Double> par) {
        setCoefficient(par.firstElement());
        for(int i=1; i<par.size(); i++) param.add(par.get(i));
    }
    
    public Point2D oblicz(Point2D point) {
        double x = point.getX(); double y = point.getY();
        if(param.get(0) == 0)
            x *= coefficient;
        else
            x = coefficient * (2.0 * Math.floor(x/param.get(0)) + 1.0)*param.get(0) - x;
        if(param.get(1) == 0)
            y *= coefficient;
        else
            y = coefficient * (2.0 * Math.floor(y/param.get(1)) + 1.0)*param.get(1) - y;
        point.setLocation(x,y);
        return point;
    }
   
    public String getName() {
        return "Rectangles";
    }
    
    @Override
    public int getParametersQuantity() { return 2; }
    
    @Override
    public String toString() {
        return getName() + getParameters().toString();
    }
}