package fflames.colouring;

import java.awt.Color;
import java.awt.image.WritableRaster;
import java.util.Vector;

public class LogRGBColoring extends LinearRGBColoring {

	public LogRGBColoring(Vector<Color> colors) {
		super(colors);
	}

	@Override
	public void finalize(WritableRaster raster) {
		int[][] screenHits = getScreenHits();
		double logMaxHits = Math.log((double)getMaxHits());
		int width = raster.getWidth();
		int height = raster.getHeight();
		
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				int hits = screenHits[x][y];
				double value = Math.log((double)hits)/logMaxHits;
				raster.setSample(x, y, 3, value * 255);
			}
		}
	}
}