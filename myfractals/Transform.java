/*
 * Transform.java
 *
 * Created on March 7, 2008, 1:38 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package myfractals;

import java.io.IOException;
import java.util.Vector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import myfractals.kolorowania.*;
/**
 *
 * @author victories
 */
public class Transform {
    private AffineTransform affineTr;
    private Vector<IWariation> wariations;
    private Double propability;

    /**
     * Creates a new instance of Transform
     * @param _affineTr transformacja afiniczna
     * @param _wariations wektor wariacji
     * @param pr prawdopodobienstwo wybrania tej transformacji
     */
    public Transform(AffineTransform _affineTr, Vector<IWariation> _wariations, Double pr) {
        affineTr = new AffineTransform(_affineTr);
        wariations = new Vector<IWariation>(_wariations);
        /* Spawdzenie czy którakolwiek z wariacji jest zale¿na od wspó³czynników 
         transformacji afinicznej. Jeœli tak to przekazanie wspó³czynników 
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
     * Tworzy now¹ instancjê klasy Transform
     * @param _affineTr transformacja afiniczna
     * @param _wariation wariacja
     * @param pr prawdopodobienstwo wybrania tej transformacji
     */
    public Transform(AffineTransform _affineTr, IWariation _wariation, Double pr)  {
        affineTr = new AffineTransform(_affineTr);
        wariations = new Vector<IWariation>(); wariations.add(_wariation);
        /* Spawdzenie czy wariacja jest zale¿na od wspó³czynników transformacji
        afinicznej. Jeœli tak to przekazanie wspó³czynników transformacji */
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
     * Funkcja zwracaj¹ca przekszta³cenie afiniczne
     * @return przekszta³cenie afiniczne
     */
    public AffineTransform getAffineTr() {
        return affineTr;
    }

    public Vector<IWariation> getWariations() {
        return wariations;
    }

    public Double getPropability() {
        return propability;
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
                name = wariations.get(i).getWariationName();
                out.write("<Name>" + name + "</Name>\r\n");
                out.write("</Wariation>\r\n");
            }
            out.write("</Wariations>\r\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
