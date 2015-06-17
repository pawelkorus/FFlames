package fflames.generator.coloring;

import java.awt.image.WritableRaster;

import fflames.generator.IColor;

public abstract class AbstractColouring implements IColor {
	private int _skippedInteractions = 0;
	private int _skipInteractions = 20;

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
