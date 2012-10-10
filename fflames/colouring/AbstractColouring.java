package fflames.colouring;

import java.awt.Color;
import java.awt.image.WritableRaster;

import fflames.interfaces.IColour;

public abstract class AbstractColouring implements IColour {
	private int _skippedInteractions = 0;
	private int _skipInteractions = 20;
	
	@Override
	public void setScreenHits(int width, int height) {
	}

	@Override
	public Color getColor(int x, int y, Color color, int whichFunction) {
		return null;
	}

	@Override
	public int getParametersQuantity() {
		return 0;
	}

	@Override
	public void initialize(WritableRaster raster) {
		_skippedInteractions = 0;
	}

	@Override
	public void finalize(WritableRaster raster) {
	}
	
	public boolean skip() {
		_skippedInteractions++;
		return _skippedInteractions <= _skipInteractions;
	}
}
