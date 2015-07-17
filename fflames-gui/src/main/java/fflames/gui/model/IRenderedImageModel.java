package fflames.gui.model;

import java.awt.image.BufferedImage;

/**
 *
 * @author Pawel Korus
 */
public interface IRenderedImageModel {
	public static final String IMAGE = "image";
	
	public BufferedImage getImage();
	
	public void setImage(BufferedImage image);
}
