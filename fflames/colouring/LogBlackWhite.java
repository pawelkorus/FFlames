/*
 * LogBlackWhite.java
 *
 * Created on March 14, 2008, 11:08 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fflames.colouring;

import java.awt.color.ColorSpace;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.WritableRaster;
/**
 *
 * @author victories
 */
public class LogBlackWhite extends AbstractColouring {   
    @Override
	public ColorModel getColorModel() {
    	return new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_GRAY), false, false, ComponentColorModel.OPAQUE, DataBuffer.TYPE_BYTE);
	}

	@Override
	public void writeColour(WritableRaster raster, int interaction, int x, int y, int index) {
		if(!skip()) {	
			if(x >= 0 && x < _width) {
				if(y >= 0 && y < _height) {
					int hits = _screenHits[x][y];
					hits++;
					_screenHits[x][y] = hits;
					if(hits > _maxHits) {
						_maxHits = hits;
					}
						
				}
			}
		}
	}

	@Override
	public void initialize(WritableRaster raster) {
		super.initialize(raster);
		
		_width = raster.getWidth();
    	_height = raster.getHeight();
    	_maxHits = 0;
    	_screenHits = new int[_width][_height];
	}

	@Override
	public void finalize(WritableRaster raster) {
		double logMaxHits = Math.log((double)_maxHits);
		
		for(int x = 0; x < _width; x++) {
			for(int y = 0; y < _height; y++) {
				int hits = _screenHits[x][y];
				double value = Math.log((double)hits)/logMaxHits;
				raster.setSample(x, y, 0, value * 255);
			}
		}
	}
    
    private int _width = 0, _height = 0, _maxHits = 0;
    private int[][] _screenHits; 
}
