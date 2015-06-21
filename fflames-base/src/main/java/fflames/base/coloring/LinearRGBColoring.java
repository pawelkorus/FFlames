package fflames.base.coloring;

import java.awt.Color;
import java.awt.color.ColorSpace;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.WritableRaster;
import java.util.ArrayList;

public class LinearRGBColoring extends DensityBasedRGBColoring {
	
	public LinearRGBColoring(ArrayList<Color> colors) {
		super(colors);
	}

	@Override
	public ColorModel getColorModel() {
		return new ComponentColorModel(
				ColorSpace.getInstance(ColorSpace.CS_sRGB), 
				true, 
				false, 
				ComponentColorModel.OPAQUE, 
				DataBuffer.TYPE_BYTE
		);
	}

	@Override
	public void writeColor(WritableRaster raster, int interaction, int x,
			int y, int index) {
		if(!skip()) {
			float[] colorComponents = getColorComponents(index);
			writeColour(raster, x, y, colorComponents);
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
				raster.setSample(x, y, 3, ((double)hits)/((double)maxHits) * 255);
			}
		}
	}

	@Override
	public void writeColor(WritableRaster raster, int interaction, int x, int y) {
		if(!skip()) {
			float[] colorComponents = getPreviousColorComponents();
			writeColour(raster, x, y, colorComponents);
			hit(x, y);
		}
	}
}
