package fflames.generator;

import java.awt.image.BufferedImage;


public class NoSuperSampling implements ISuperSampling {

	public NoSuperSampling(int targetWidth, int targetHeight) {
		_targetWidth = targetWidth;
		_targetHeight = targetHeight;
	}
	
	@Override
	public int getRequiredWidth() {
		return _targetWidth;
	}

	@Override
	public int getRequiredHeight() {
		return _targetHeight;
	}

	@Override
	public BufferedImage processImage(BufferedImage image) {
		return image;
	}

	private int _targetWidth, _targetHeight;
}
