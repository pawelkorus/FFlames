/*
 * Transform.java
 *
 * Created on March 7, 2008, 1:38 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fflames.model;

import java.io.IOException;
import java.util.Vector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import fflames.interfaces.IVariation;
/**
 *
 * @author victories
 */
public class Transform {
    private AffineTransform affineTr;
    private Vector<IVariation> wariations;
    private Double propability;

    
    /**
     * Creates empty transform with propability 0.0
     */
    public Transform() {
    	affineTr = new AffineTransform();
    	wariations = new Vector<IVariation>();
    	propability = new Double(0.0);
    }
    
    /**
     * Creates a new instance of Transform
     * @param _affineTr transformacja afiniczna
     * @param _wariations wektor wariacji
     * @param pr prawdopodobienstwo wybrania tej transformacji
     */
    public Transform(AffineTransform _affineTr, Vector<IVariation> _wariations, Double pr) {
        affineTr = new AffineTransform(_affineTr);
        wariations = new Vector<IVariation>(_wariations);
        /* Spawdzenie czy kt�rakolwiek z wariacji jest zale�na od wsp�czynnik�w 
         transformacji afinicznej. Je�li tak to przekazanie wsp�czynnik�w 
         transformacji */
        for(int i = 0; i < wariations.size(); i++) {
            if(wariations.get(i).isDependent()) {
                Vector<Double> temp = new Vector<Double>();
                double[] parameters = new double[6];
                affineTr.getMatrix(parameters);
                for(double par:parameters) temp.add(par);
                if(wariations.get(i).getParametersQuantity() > 0)
                    temp.addAll(wariations.get(i).getParameters().subList(2, 
                            wariations.get(i).getParameters().size()));    
                wariations.get(i).setParameters(temp);
            }
        }
        propability = new Double(pr);
    }
    
    /**
     * Tworzy now� instancj� klasy Transform
     * @param _affineTr transformacja afiniczna
     * @param _wariation wariacja
     * @param pr prawdopodobienstwo wybrania tej transformacji
     */
    public Transform(AffineTransform _affineTr, IVariation _wariation, Double pr)  {
        affineTr = new AffineTransform(_affineTr);
        wariations = new Vector<IVariation>(); wariations.add(_wariation);
        /* Spawdzenie czy wariacja jest zależna od współczynników transformacji
        afinicznej. Jeśli tak to przekazanie wspóczynników transformacji */
        if(wariations.firstElement().isDependent()) {
            Vector<Double> temp = new Vector<Double>();
            double[] parameters = new double[6];
            affineTr.getMatrix(parameters);
            for(double par:parameters) temp.add(par);
            temp.addAll(wariations.firstElement().getParameters().subList(2, 
                    wariations.firstElement().getParameters().size()));
            wariations.firstElement().setParameters(temp);
        }
        propability = new Double(pr);
    }
    
    private Point2D pointSum(Point2D a, Point2D b) {
        a.setLocation(a.getX() + b.getX(), a.getY() + b.getY());
        return a;
    };
    
    /**
     * 
     * @param point
     * @return
     */
    public Point2D oblicz(Point2D point) {
        affineTr.transform(point, point);
        Point2D temp = new Point2D.Double(0.0, 0.0);
        for(int i=0; i < wariations.size(); i++) {
            temp.setLocation(pointSum(temp, wariations.get(i).oblicz(point)));
        }
        return temp;
    }
    
    @Override
    public String toString() {
        return affineTr.toString() + wariations.toString();
    }

    /**
     * Funkcja zwracaj�ca przekszta�cenie afiniczne
     * @return przekszta�cenie afiniczne
     */
    public AffineTransform getAffineTr() {
        return affineTr;
    }

    public Vector<IVariation> getWariations() {
        return wariations;
    }

    public Double getPropability() {
        return propability;
    }
    
    public void setPropability(Double value) {
    	propability = value;
    }
    
    public void writeXML(java.io.OutputStreamWriter out) {
        double[] temp = new double[6];
        affineTr.getMatrix(temp);
        String name = null; Vector<Double> par = null;
        try {
            out.write("<Propability>" + propability.toString() + "</Propability>\r\n");
            out.write("<AffineTransform>\r\n");
            for(int i=0; i<temp.length; i++) {
                out.write("<Wsp>" + temp[i] + "</Wsp>\r\n");
            }
            out.write("</AffineTransform>\r\n");
            
            out.write("<Wariations>\r\n");
            for(int i=0; i<wariations.size(); i++) {
                out.write("<Wariation>\r\n");
                par = wariations.get(i).getParameters();
                out.write("<Coefficient>" + par.firstElement() + "</Coefficient>\r\n");
                if(par.size() > 1) {
                    for(int j=1; j<par.size(); j++) {
                        out.write("<Par>" + par.get(j) + "</Par>\r\n");
                    }
                }
                name = wariations.get(i).getName();
                out.write("<Name>" + name + "</Name>\r\n");
                out.write("</Wariation>\r\n");
            }
            out.write("</Wariations>\r\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
