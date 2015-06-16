/*
 * LinearBlackWhite.java
 *
 * Created on March 14, 2008, 1:29 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fflames.generator.colouring;

import java.awt.color.ColorSpace;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.WritableRaster;
/**
 *
 * @author victories
 */
public class LinearBlackWhite extends DensityBasedColoring {
    @Override
	public ColorModel getColorModel() {
		return new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_GRAY), false, false, ComponentColorModel.OPAQUE, DataBuffer.TYPE_BYTE);
	}

	@Override
	public void writeColour(WritableRaster raster, int interaction, int x, int y, int index) {
		if(!skip()) {
			hit(x, y);
		}
	}

	@Override
	public void finalize(WritableRaster raster) {
		int[][] screenHits = getScreenHits();
		int maxHits = getMaxHits();
		int width = raster.getWidth();
		int height = raster.getHeight();
		
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				int hits = screenHits[x][y];
				raster.setSample(x, y, 0, ((double)hits)/((double)maxHits) * 255);
			}
		}
	}

	@Override
	public void writeColour(WritableRaster raster, int interaction, int x, int y) {
		writeColour(raster, interaction, x, y, 0);
	}
}
