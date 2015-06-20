package fflames.generator;

import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public interface IColoring {

	/**
	 * If coloring can be customized by providing color definitions
	 * then this method should return true.
	 * 
	 * @return true if colors can be customized. Otherwise false
	 */
	public boolean supportsCustomColors();

	public void initialize(WritableRaster raster);

	public void finalize(WritableRaster raster);

	public ColorModel getColorModel();

	public void writeColor(WritableRaster raster, int interaction, int x, int y);

	public void writeColor(WritableRaster raster, int interaction, int x, int y, int index);
}
