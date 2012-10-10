/*
 * LinearBlackWhite.java
 *
 * Created on March 14, 2008, 1:29 PM
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
public class LinearBlackWhite extends AbstractColouring {
    @Override
	public ColorModel getColorModel() {
		return new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_GRAY), false, false, ComponentColorModel.OPAQUE, DataBuffer.TYPE_BYTE);
	}



	@Override
	public void writeColour(WritableRaster raster, int interaction, int x, int y, int index) {
		if(!skip()) {
			if(x >= 0 && x < _width) {
				if(y >= 0 && y < _height) {
					_screenHits[x][y] = _screenHits[x][y] + 1;
					int hits = _screenHits[x][y];
					if(hits > _maxHits) {
						_maxHits = hits;
					}
				}
			}
		}
	}



	@Override
	public void finalize(WritableRaster raster) {
		for(int x = 0; x < _width; x++) {
			for(int y = 0; y < _height; y++) {
				int hits = _screenHits[x][y];
				raster.setSample(x, y, 0, ((double)hits)/((double)_maxHits) * 255);
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
    
    private int[][] _screenHits;
    private int _width = 0, _height = 0, _maxHits = 0;
}
