package fflames.base;

import java.awt.image.BufferedImage;

public interface ISuperSampling {

	public abstract int getRequiredWidth();

	public abstract int getRequiredHeight();

	public abstract BufferedImage processImage(BufferedImage image);

}