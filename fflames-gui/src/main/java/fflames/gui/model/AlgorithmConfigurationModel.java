package fflames.gui.model;

import java.io.Serializable;

public class AlgorithmConfigurationModel extends AbstractModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String ROTATIONS_NUMBER = "rotations_number";
	public static final String ITERATIONS_NUMBER = "iterations_number";
	public static final String IMAGE_WIDTH = "image_width";
	public static final String IMAGE_HEIGHT = "image_height";
	public static final String SUPER_SAMPLING = "super_sampling";
	
	public int getImageWidth() {
		return (Integer) getParam(IMAGE_WIDTH, 640);
	}
	
	public void setImageWidth(int value) {
		setParam(IMAGE_WIDTH, value);
	}
	
	public int getImageHeight() {
		return (Integer) getParam(IMAGE_HEIGHT, 480);
	}
	
	public void setImageHeight(int value) {
		setParam(IMAGE_HEIGHT, value);
	}
	
	public int getRotationsNumber() {
		return (Integer) getParam(ROTATIONS_NUMBER, 0);
	}
	
	public void setRotationsNumber(int value) {
		setParam(ROTATIONS_NUMBER, value);
	}
	
	public int getIterationsNumber() {
		return (Integer) getParam(ITERATIONS_NUMBER, 100000);
	}
	
	public void setItarationsNumber(int value) {
		setParam(ITERATIONS_NUMBER, value);
	}
	
	public void setSuperSampling(int value) {
		setParam(SUPER_SAMPLING, value);
	}
	
	public int getSuperSampling() {
		return (Integer) getParam(SUPER_SAMPLING, 1);
	}
}
