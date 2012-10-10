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
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
/**
 *
 * @author victories
 */
public interface IColour {
    @Deprecated
	public void setScreenHits(int width, int height);
    @Deprecated
    public Color getColor(int x, int y, Color color, int whichFunction);
    
    public int getParametersQuantity(); 
    
    public void initialize(WritableRaster raster);
    public void finalize(WritableRaster raster);    
    
    public ColorModel getColorModel();
    public void writeColour(WritableRaster raster, int interaction, int x, int y, int index);
}
