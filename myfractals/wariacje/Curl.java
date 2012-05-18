/*
 * Curl.java
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
public class Curl extends AbstractWariation {
    /** Creates a new instance of Curl
    * @param _coefficient wartoœæ wspó³czynnika
    */
    public Curl(Double _coefficient) {
        coefficient = _coefficient;
    }
    
    /** Creates a new instance of Curl
     * 
     * @param par wektor zawieraj¹cy wartoœci parametrów. Na pierwszym miejscu
     * powinien znajdowaæ siêwspó³czynnik.
     */
    public Curl(Vector<Double> par) {
        setCoefficient(par.firstElement());
        for(int i=1; i<par.size(); i++) param.add(par.get(i));
    }
    
    public Point2D oblicz(Point2D point) {
        double x = point.getX(); double y = point.getY();
        double t1 = 1 + param.get(0)*x + param.get(1)*(x*x + y*y);
        double t2 = param.get(0) * y + 2.0*param.get(1)*x*y;
        double k = 1/(t1*t1 + t2*t2);
        point.setLocation(k*(t1*x+y*t2)*coefficient, k*(y*t1-x*t2)*coefficient);
        return point;
    }
   
    public String getWariationName() {
        return "Curl";
    }
    
    @Override
    public int getParametersQuantity() { return 2; }
    
    @Override
    public String toString() {
        return getWariationName() + getParameters().toString();
    }
}