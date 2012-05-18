/*
 * IWariation.java
 *
 * Created on March 5, 2008, 11:09 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package myfractals;

import java.awt.geom.Point2D;
import javax.swing.*;
import java.util.Vector;

/**
 * Interfejs wariacji
 * @author victories
 */
public interface IWariation {
    /**
     * Funkcja zwracaj¹ca wspó³czynnik wariacji
     * @return Wartoœæ double wspó³czynnika
     */
    public Double getCoefficient();
    
    /**
     * Ustawia wartoœæ wspó³czynnika dla danej wariacji 
     * @param _coefficient wartoœæ nowego wspó³czynnika
     */
    public void setCoefficient(Double _coefficient);
    
    /**
     * Funkcja obliczaj¹ca wspó³rzêdne nowego punktu przez przekszta³cenie 
     * obiektu wejœciowego przez odpowiedni¹ wariacje
     * @param point wspo³rzêdne przekszta³canego punktu 
     * @return Obiekt typu Point2D o nowych wspó³rzêdnych
     */
    public Point2D oblicz(Point2D point);
    
    /**
     * Funkcja zwracaj¹ca iloœæ dodatkowych parametrów
     * @return wartoœæ int oznaczaj¹ca iloœæ dodatkowych parametrów
     */
    public int getParametersQuantity();
    
    /**
     * Funkcja zmieniaj¹ca parametry 
     * @param parameters obiekt typu Vector<Double> zawieraj¹cy nowe parametry
     */
    public void setParameters(Vector<Double> parameters);
    
    /**
     * Funkcja zwracaj¹ca dla danej wariacji ustawione parametry. Na pierwszej
     * pozycji znajduje siê wspó³czynnik, na dalszych dodatkowe paramtry
     * @return Wektro zawieraj¹cy wartoœci dodatkowych 
     * parametów
     */
    public Vector<Double> getParameters();
    
    /**
     * Funkcja zwracaj¹ca nazwê wariacji
     * @return Obiekt typu String zawiraj¹cy nazwê wariacji
     */
    public String getWariationName();
    
    
    /**
     * Funkcja ta okreœla czy dana wariacja jest zale¿na od wspó³czynników
     * transformacji afinicznej.
     * @return true jeœli transformacja jest zale¿na od wspó³czynników
     * przekszta³cenia afinicznego. W innym wypadku zwraca wartoœæ false.
     */
    public boolean isDependent();
}
