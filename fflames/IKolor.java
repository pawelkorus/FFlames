/*
 * IKolor.java
 *
 * Created on March 14, 2008, 10:36 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fflames;

import java.awt.Color;
/**
 *
 * @author victories
 */
public interface IKolor {
    public void zeruj();
    public void setScreenHits(int width, int height);
    public Color getColor(int x, int y, Color color, int whichFunction);
    public int getParametersQuantity();
}
