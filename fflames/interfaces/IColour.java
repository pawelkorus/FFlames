/*
 * IColour.java
 *
 * Created on March 14, 2008, 10:36 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fflames.interfaces;

import java.awt.Color;
/**
 *
 * @author victories
 */
public interface IColour {
    public void zeruj();
    public void setScreenHits(int width, int height);
    public Color getColor(int x, int y, Color color, int whichFunction);
    public int getParametersQuantity();
}
