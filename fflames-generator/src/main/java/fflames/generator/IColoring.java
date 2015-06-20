package fflames.generator;

import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public interface IColoring {

	public int getParametersQuantity();

	public void initialize(WritableRaster raster);

	public void finalize(WritableRaster raster);

	public ColorModel getColorModel();

	public void writeColor(WritableRaster raster, int interaction, int x, int y);

	public void writeColor(WritableRaster raster, int interaction, int x, int y, int index);
}
