package fflames.generator.coloring;

import java.awt.image.WritableRaster;

abstract public class DensityBasedColoring extends AbstractColoring {
	
	@Override
	public void initialize(WritableRaster raster) {
		_width = raster.getWidth();
		_height = raster.getHeight();
		_maxHits = 0;
		_screenHits = new int[_width][_height];
	}
	
	public boolean hit(int x, int y) {
		if(x >= 0 && x < _width) {
			if(y >= 0 && y < _height) {
				_screenHits[x][y] = _screenHits[x][y] + 1;
				int hits = _screenHits[x][y];
				if(hits > _maxHits) {
					_maxHits = hits;
				}
				
				return true;
			}
		}
		
		return false;
	}
	
	public int getMaxHits() {
		return _maxHits;
	}
	
	public int[][] getScreenHits() {
		return _screenHits;
	}
	
	private int[][] _screenHits;
	private int _width = 0, _height = 0, _maxHits = 0;
}
