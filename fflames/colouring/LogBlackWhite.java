/*
 * LogBlackWhite.java
 *
 * Created on March 14, 2008, 11:08 PM
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
public class LogBlackWhite implements fflames.interfaces.IColour{
    private int width, height, maxHits;
    private int[][] screenHits; 
    /** Creates a new instance of LogBlackWhite */
    public LogBlackWhite() {
        width = 0; height = 0;
        screenHits = new int[width][height];
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
            maxHits = 0;
        }
    }
    
    public Color getColor(int x, int y, Color color, int whichFunction) { 
        screenHits[x][y] += 1;
        if(screenHits[x][y] > maxHits) maxHits = screenHits[x][y];
        return new Color(1.0f, 1.0f, 1.0f, (float)(Math.log10(screenHits[x][y])/Math.log10(maxHits)));
    }
    
    public int getParametersQuantity() { return 0; }
}
