/*
 * SimpleColoring.java
 *
 * Created on March 14, 2008, 11:41 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fflames.colouring;

import java.awt.Color;
import java.awt.color.ColorSpace;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.WritableRaster;
import java.util.Random;
import java.util.Vector;
/**
 *
 * @author victories
 */
public class RGBColoring extends AbstractColouring {
	
	public RGBColoring(Vector<Color> colors) {
        _colors = colors;
    }
	
    @Override
	public void initialize(WritableRaster raster) {
    	super.initialize(raster);
    	
    	Random _randomGenerator = new Random();
    	
		_lastColor = new float[3];
		_lastColor[0] = _randomGenerator.nextFloat();
		_lastColor[1] = _randomGenerator.nextFloat();
		_lastColor[2] = _randomGenerator.nextFloat();
	}
    
    public int getParametersQuantity() { return 2; }

	@Override
	public ColorModel getColorModel() {
		return new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB), false, false, ComponentColorModel.OPAQUE, DataBuffer.TYPE_BYTE);
	}

	@Override
	public void writeColour(WritableRaster raster, int interaction, int x, int y, int index) {
		float[] colorComponents = _colors.get(index).getRGBColorComponents(null);
		
		_lastColor[0] = (_lastColor[0] + colorComponents[0])*0.5f;
		_lastColor[1] = (_lastColor[1] + colorComponents[1])*0.5f;
		_lastColor[2] = (_lastColor[2] + colorComponents[2])*0.5f;
		
		if(!skip()) {	
			raster.setSample(x, y, 0, (byte)(_lastColor[0] * (float)255));
			raster.setSample(x, y, 1, (byte)(_lastColor[1] * (float)255));
			raster.setSample(x, y, 2, (byte)(_lastColor[2] * (float)255));
		}
	}
	
	@Override
	public void writeColour(WritableRaster raster, int interaction, int x, int y) {
		if(!skip()) {	
			raster.setSample(x, y, 0, (byte)(_lastColor[0] * (float)255));
			raster.setSample(x, y, 1, (byte)(_lastColor[1] * (float)255));
			raster.setSample(x, y, 2, (byte)(_lastColor[2] * (float)255));
		}
	}
	
    private Vector<Color> _colors;
    private float[] _lastColor;
}
