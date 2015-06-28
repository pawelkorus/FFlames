package fflames.gui.model;

import java.awt.geom.AffineTransform;

public class AffineTransformModel extends AbstractModel {
	
	public static final String SHEAR_X = "shear_x";
	public static final String SHEAR_Y = "shear_y";
	public static final String TRANSLATE_X = "translate_x";
	public static final String TRANSLATE_Y = "translate_y";
	public static final String SCALE_X = "scale_x";
	public static final String SCALE_Y = "scale_y";
	public static final String TRANSFORM = "transform"; 
	
	public AffineTransformModel() {
		reset();
	}
	
	public double getScaleX() {
		return (Double) getParam(SCALE_X);
	}
	
	public void setScaleX(double v) {
		setParam(SCALE_X, v);
	}
	
	public double getScaleY() {
		return (Double) getParam(SCALE_Y);
	}
	
	public void setScaleY(double v) {
		setParam(SCALE_Y, v);
	}
	
	public double getTranslateX() {
		return (Double) getParam(TRANSLATE_X);
	}
	
	public void setTranslateX(double v) {
		setParam(TRANSLATE_X, v);
	}
	
	public double getTranslateY() {
		return (Double) getParam(TRANSLATE_Y);
	}
	
	public void setTranslateY(double v) {
		setParam(TRANSLATE_Y, v);
	}
	
	public double getShearX() {
		return (Double) getParam(SHEAR_X);
	}
	
	public void setShearX(double v) {
		setParam(SHEAR_X, v);
	}
	
	public double getShearY() {
		return (Double) getParam(SHEAR_Y);
	}
	
	public void setShearY(double v) {
		setParam(SHEAR_Y, v);
	}

	
	/**
	 * Returns instance of the AffineTransform class initialized
	 * with current parameter values
	 * 
	 * @return new instance of AffineTransform class
	 */
	public AffineTransform getTransform() {
		double[] params = {
			getScaleX(), getShearY(), 
			getShearX(), getScaleY(),
			getTranslateX(), getTranslateY()
		};
		return new AffineTransform(params);
	}
	
	public void setTransform(AffineTransform transform) {
		setScaleX(transform.getScaleX());
		setScaleY(transform.getScaleY());
		setShearX(transform.getShearX());
		setShearY(transform.getShearY());
		setTranslateX(transform.getTranslateX());
		setTranslateY(transform.getTranslateY());
	}
	
	public void reset() {
		setTransform(new AffineTransform());
	}
}
