package fflames.colouring;

import java.awt.Color;
import java.awt.image.WritableRaster;
import java.util.Vector;

abstract public class DensityBasedRGBColoring extends DensityBasedColoring {
	
	public DensityBasedRGBColoring(Vector<Color> colors) {
		_colors = colors;
	}
	
	protected boolean mixColorComponents(float[] current, float[] mix) {
		try {
			for(int i = 0; i < current.length; i++) {
				current[i] = (current[i] + mix[i])*0.5f;
			}
			
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	protected boolean writeColour(WritableRaster raster, int x, int y, float[] colorComponents) {
		try {
			for(int i = 0; i < colorComponents.length; i++) {
				raster.setSample(x, y, i, (byte)(colorComponents[i] * (float)255));
			}
			
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	protected float[] getColorComponents(int index) {
		return _colors.get(index).getRGBColorComponents(null);
	}
	
	private Vector<Color> _colors;
}