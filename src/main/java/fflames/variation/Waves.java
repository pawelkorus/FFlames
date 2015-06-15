/*
 * Waves.java
 *
 * Created on March 11, 2008, 6:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fflames.variation;

import java.awt.geom.Point2D;
/** Wariacja typu Waves.
 * Wariacja ta jest zale�na od parametr�w transformacji afinicznej. Jednak
 * parametry te nie s� podawane jak parametry dodatkowe konkretnej
 * transformacji. St�d te� funkcja toString() zwraca tylko wsp�czynnik
 * dla tej transformacji a funkcja getParametersQuantity() warto�� 0. Jednak
 * warto�ci wsp�czynnik�w przekszta�cenia b�d� przechowywane w zmiennej
 * paraments
 *
 * @author victories
 */
public class Waves extends AbstractWariation {
       
    /** Creates a new instance of Waves
     * @param _coefficient wsp�czynnik wariacji
     */
    public Waves(Double _coefficient) {
        coefficient = new Double(_coefficient);
    }
    
    @Override
    public String toString() {
        return getName() + getParameters().firstElement().toString();
    }
    
    public Point2D oblicz(Point2D point) {
        double x = point.getX(); double y = point.getY();
        //Ma�e oszustwo jezeli c i f s� r�wne 0
        double c = param.get(4);// if(c == 0) c = 0.0000001;
        double f = param.get(5);// if(f == 0) f = 0.0000001;
        point.setLocation( (x + param.get(2)*Math.sin(y/((c*c) + 0.000001))) * coefficient,
                (y + param.get(3)*Math.sin(x/((f*f) + 0.000001)) * coefficient) );
        return point;
    }
    
    public String getName() { return "Waves"; }
    
    @Override
    public boolean isDependent() {
        return true;
    }
}
