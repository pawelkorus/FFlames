/*
 * BlackWhite.java
 *
 * Created on March 14, 2008, 11:24 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package myfractals.kolorowania;

import java.awt.Color;
import java.awt.geom.Point2D;
/**
 *
 * @author victories
 */
public class BlackWhite implements myfractals.IKolor{
    
    /** Creates a new instance of BlackWhite */
    public BlackWhite() {
    }
    
    public void zeruj() {}
    public void setScreenHits(int width, int height) {}
    
    public Color getColor(int x, int y, Color color, int whichFunction) { return Color.WHITE; }
    
    public int getParametersQuantity() { return 0; }
}
