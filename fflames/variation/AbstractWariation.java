/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fflames.variation;

import java.util.Vector;

import fflames.IWariation;

/** Klasa abstrakcyjna dla wariacji
 *
 * @author Victories
 */
public abstract class AbstractWariation implements IWariation {
    /**
     * 
     */
    protected Vector<Double> param = null;
    protected Double coefficient;
    
    public Double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Double _coefficient) {
        coefficient = new Double(_coefficient);
    }

    public int getParametersQuantity() {
        return 0;
    }

    public void setParameters(Vector<Double> parameters) { 
        param = new Vector<Double>(parameters);
    }

    public Vector<Double> getParameters() {
        Vector<Double> temp = new Vector<Double>();
        temp.add(coefficient);
        if(param != null) {
            temp.addAll(param);
        }
        return temp;
    }
    
    public boolean isDependent() {
        return false;
    }
}
