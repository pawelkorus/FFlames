/*
 * Waves.java
 *
 * Created on March 11, 2008, 6:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package myfractals.wariacje;

import java.awt.geom.Point2D;
/** Wariacja typu Waves.
 * Wariacja ta jest zale¿na od parametrów transformacji afinicznej. Jednak
 * parametry te nie s¹ podawane jak parametry dodatkowe konkretnej
 * transformacji. St¹d te¿ funkcja toString() zwraca tylko wspó³czynnik
 * dla tej transformacji a funkcja getParametersQuantity() wartoœæ 0. Jednak
 * wartoœci wspó³czynników przekszta³cenia bêd¹ przechowywane w zmiennej
 * paraments
 *
 * @author victories
 */
public class Waves extends AbstractWariation {
       
    /** Creates a new instance of Waves
     * @param _coefficient wspó³czynnik wariacji
     */
    public Waves(Double _coefficient) {
        coefficient = new Double(_coefficient);
    }
    
    @Override
    public String toString() {
        return getWariationName() + getParameters().firstElement().toString();
    }
    
    public Point2D oblicz(Point2D point) {
        double x = point.getX(); double y = point.getY();
        //Ma³e oszustwo jezeli c i f s¹ równe 0
        double c = param.get(4);// if(c == 0) c = 0.0000001;
        double f = param.get(5);// if(f == 0) f = 0.0000001;
        point.setLocation( (x + param.get(2)*Math.sin(y/((c*c) + 0.000001))) * coefficient,
                (y + param.get(3)*Math.sin(x/((f*f) + 0.000001)) * coefficient) );
        return point;
    }
    
    public String getWariationName() { return "Waves"; }
    
    @Override
    public boolean isDependent() {
        return true;
    }
}
