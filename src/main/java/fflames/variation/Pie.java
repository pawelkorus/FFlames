/*
 * Pie.java
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
public class Pie extends AbstractWariation {
    /** Creates a new instance of Pie
    * @param _coefficient warto�� wsp�czynnika
    */
    public Pie(Double _coefficient) {
        coefficient = _coefficient;
    }
    
    /** Creates a new instance of Pie
     * 
     * @param par wektor zawieraj�cy warto�ci parametr�w. Na pierwszym miejscu
     * powinien znajdowa� si�wsp�czynnik.
     */
    public Pie(Vector<Double> par) {
        setCoefficient(par.firstElement());
        for(int i=1; i<par.size(); i++) param.add(par.get(i));
    }
    
    public Point2D oblicz(Point2D point) {
        double s1 = Math.floor(Math.random() * param.get(0) + 0.5);
        double a = param.get(1) + 2*Math.PI*(s1 + Math.random()* param.get(2))/param.get(0);
        double r = coefficient*Math.random();
        point.setLocation(r*Math.cos(a), r*Math.sin(a));
        return point;
    }
   
    public String getName() {
        return "Pie";
    }
    
    @Override
    public int getParametersQuantity() { return 3; }
    
    @Override
    public String toString() {
        return getName() + getParameters().toString();
    }
}