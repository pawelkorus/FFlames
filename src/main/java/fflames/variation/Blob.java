/*
 * Blob.java
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
public class Blob extends AbstractWariation {
    /** Creates a new instance of Blob
    * @param _coefficient warto�� wsp�czynnika
    */
    public Blob(Double _coefficient) {
        coefficient = _coefficient;
    }
    
    /** Creates a new instance of Blob
     * 
     * @param par wektor zawieraj�cy warto�ci parametr�w. Na pierwszym miejscu
     * powinien znajdowa� si�wsp�czynnik.
     */
    public Blob(Vector<Double> par) {
        setCoefficient(par.firstElement());
        for(int i=1; i<par.size(); i++) param.add(par.get(i));
    }
    
    public Point2D oblicz(Point2D point) {
        Double r = Math.sqrt(Math.pow(point.getX(), 2) + Math.pow(point.getY(), 2));
        Double teta = Math.atan2(point.getX(), point.getY());
        Double wsp = r*(param.get(1) + ((param.get(0)-param.get(1))/2.0))*(Math.sin(param.get(2)*teta)+1);
        point.setLocation(wsp * Math.cos(teta) * coefficient, wsp*Math.sin(teta)*coefficient);
        return point;
    }
   
    public String getName() {
        return "Blob";
    }
    
    @Override
    public int getParametersQuantity() { return 3; }
    
    @Override
    public String toString() {
        return getName() + getParameters().toString();
    }
}