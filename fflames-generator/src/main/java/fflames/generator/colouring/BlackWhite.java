/*
 * BlackWhite.java
 *
 * Created on March 14, 2008, 11:24 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fflames.generator.colouring;

import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
/**
 *
 * @author victories
 */
public class BlackWhite extends AbstractColouring {
		
	@Override
	public ColorModel getColorModel() {
		byte[] colors = {0, (byte)255}; 
		
		return new IndexColorModel(2, 2, colors, colors, colors);
	}

	@Override
	public void writeColour(WritableRaster raster, int interaction, int x, int y, int index) {
		if(!skip()) {
			raster.setSample(x, y, 0, 1);
		}
	}

	@Override
	public void writeColour(WritableRaster raster, int interaction, int x, int y) {
		writeColour(raster, interaction, x, y, 0);
	}
}
