package fflames.gui.model;

import java.awt.image.BufferedImage;

/**
 *
 * @author p.korus
 */
public class RenderedImageModel extends AbstractModel
	implements IRenderedImageModel {
	

	@Override
	public BufferedImage getImage() {
		return (BufferedImage) getParam(IMAGE);
	}
	
	@Override
	public void setImage(BufferedImage image) {
		setParam(IMAGE, image);
	}
}
