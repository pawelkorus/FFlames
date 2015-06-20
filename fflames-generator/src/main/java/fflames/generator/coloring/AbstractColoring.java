package fflames.generator.coloring;

import java.awt.image.WritableRaster;

import fflames.generator.IColoring;

public abstract class AbstractColoring implements IColoring {
	private int _skippedInteractions = 0;
	private final int _skipInteractions = 20;

	@Override
	public boolean supportsCustomColors() {
		return false;
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
