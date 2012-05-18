/*
 * Pie.java
 *
 * Created on March 10, 2008, 6:53 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package myfractals.wariacje;

import java.awt.geom.Point2D;
import java.util.Vector;
/**
 *
 * @author victories
 */
public class Pie extends AbstractWariation {
    /** Creates a new instance of Pie
    * @param _coefficient wartoœæ wspó³czynnika
    */
    public Pie(Double _coefficient) {
        coefficient = _coefficient;
    }
    
    /** Creates a new instance of Pie
     * 
     * @param par wektor zawieraj¹cy wartoœci parametrów. Na pierwszym miejscu
     * powinien znajdowaæ siêwspó³czynnik.
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
   
    public String getWariationName() {
        return "Pie";
    }
    
    @Override
    public int getParametersQuantity() { return 3; }
    
    @Override
    public String toString() {
        return getWariationName() + getParameters().toString();
    }
}