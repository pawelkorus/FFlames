/*
 * LinearBlackWhite.java
 *
 * Created on March 14, 2008, 1:29 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fflames.colouring;

import java.awt.Color;
/**
 *
 * @author victories
 */
public class LinearBlackWhite implements fflames.interfaces.IKolor {
    int width, height;
    private int[][] screenHits;
    /** Creates a new instance of LinearBlackWhite */
    public LinearBlackWhite() {
    }

    public void zeruj() {
        for(int i=0; i<width; i++)
            for(int j=0; j<height; j++)
                screenHits[i][j] = 0;
    }
    
    public void setScreenHits(int _width, int _height) {
        if((_width != width) || (_height != height)) {
            width = _width; height =_height;
            screenHits = new int[width][height];
        }
    }
    
    public Color getColor(int x, int y, Color color, int whichFunction) { 
         if(screenHits[x][y] < 255)
            screenHits[x][y] += 1;
        return new Color(255, 255, 255, screenHits[x][y]);
    }
    
    public int getParametersQuantity() { return 0; }
}
