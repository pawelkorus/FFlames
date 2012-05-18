/*
 * RadialBlur.java
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
public class RadialBlur extends AbstractWariation {
    /** Creates a new instance of RadialBlur
    * @param _coefficient wartoœæ wspó³czynnika
    */
    public RadialBlur(Double _coefficient) {
        coefficient = _coefficient;
    }
    
    /** Creates a new instance of RadialBlur
     * 
     * @param par wektor zawieraj¹cy wartoœci parametrów. Na pierwszym miejscu
     * powinien znajdowaæ siêwspó³czynnik.
     */
    public RadialBlur(Vector<Double> par) {
        setCoefficient(par.firstElement());
        for(int i=1; i<par.size(); i++) param.add(par.get(i));
    }
    
    /* Mam w±tpliwo¶ci, czy to akurat tak siêoblicza */
    public Point2D oblicz(Point2D point) {
        double x = point.getX(); double y = point.getY();
        double spinvar = Math.sin(param.get(0) * Math.PI/2.0);
        double zoomvar = Math.cos(param.get(0) * Math.PI/2.0);
        
        double rndG = Math.random() + Math.random() + Math.random() + Math.random() - 2.0;
        rndG *= coefficient;
        double ra = Math.sqrt(x*x + y*y);
        double tmpa = Math.atan2(y, x) + spinvar*rndG;
        double sa = Math.sin(tmpa);
        double ca = Math.cos(tmpa);
        double rz = zoomvar*rndG - 1.0;
        
        point.setLocation(ra*ca+x*rz, ra*sa+y*rz);
        
        return point;
    }
   
    public String getWariationName() {
        return "RadialBlur";
    }
    
    @Override
    public int getParametersQuantity() { return 1; }
    
    @Override
    public String toString() {
        return getWariationName() + getParameters().toString();
    }
}