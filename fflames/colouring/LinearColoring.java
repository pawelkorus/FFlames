/*
 * LinearColoring.java
 *
 * Created on March 14, 2008, 11:41 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fflames.colouring;

import java.awt.Color;
import java.util.Vector;
/**
 *
 * @author victories
 */
public class LinearColoring implements fflames.IKolor {
    int width, height;
    private int[][] screenHits;
    private Vector<Color> colors;
    /** Creates a new instance of LinearBlackWhite */
    public LinearColoring(Vector<Color> _colors) {
        colors = _colors;
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
        return new Color((color.getRed()/255.0f + colors.get(whichFunction).getRed()/255.0f)/2.0f, 
                         (color.getBlue()/255.0f + colors.get(whichFunction).getBlue()/255.0f)/2.0f,
                          (color.getGreen()/255.0f + colors.get(whichFunction).getGreen()/255.0f)/2.0f,
                            screenHits[x][y]/255.0f); 
    }
    
    public int getParametersQuantity() { return 2; }
}
