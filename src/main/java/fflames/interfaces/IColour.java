/*
 * IColour.java
 *
 * Created on March 14, 2008, 10:36 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fflames.interfaces;

import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

/**
 *
 * @author victories
 */
public interface IColour {
    public int getParametersQuantity(); 
    
    public void initialize(WritableRaster raster);
    public void finalize(WritableRaster raster);    
    
    public ColorModel getColorModel();
    public void writeColour(WritableRaster raster, int interaction, int x, int y);
    public void writeColour(WritableRaster raster, int interaction, int x, int y, int index);
}
