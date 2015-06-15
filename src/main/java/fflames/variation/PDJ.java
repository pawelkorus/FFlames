/*
 * PDJ.java
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
public class PDJ extends AbstractWariation {
    /** Creates a new instance of PDJ
    * @param _coefficient warto�� wsp�czynnika
    */
    public PDJ(Double _coefficient) {
        coefficient = _coefficient;
    }
    
    /** Creates a new instance of PDJ
     * 
     * @param par wektor zawieraj�cy warto�ci parametr�w. Na pierwszym miejscu
     * powinien znajdowa� si�wsp�czynnik.
     */
    public PDJ(Vector<Double> par) {
        setCoefficient(par.firstElement());
        for(int i=1; i<par.size(); i++) param.add(par.get(i));
    }
    
    public Point2D oblicz(Point2D point) {
        double x = point.getX(); double y = point.getY();
        point.setLocation((Math.sin(param.get(0)*y) - Math.cos(param.get(1)*x)) * coefficient, 
                            (Math.sin(param.get(2)*x) - Math.cos(param.get(3)*y)) *coefficient);
        return point;
    }
   
    public String getName() {
        return "PDJ";
    }
    
    @Override
    public int getParametersQuantity() { return 4; }
    
    @Override
    public String toString() {
        return getName() + getParameters().toString();
    }
}