/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fflames.gui.model;

import java.awt.image.BufferedImage;

/**
 *
 * @author p.korus
 */
public class RenderedImageModel extends AbstractModel {
	public static final String IMAGE = "image_changed";

	public BufferedImage getImage() {
		return (BufferedImage) getParam(IMAGE);
	}
	
	public void setImage(BufferedImage image) {
		setParam(IMAGE, image);
	}
}
