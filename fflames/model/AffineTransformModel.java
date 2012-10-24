package fflames.model;

import java.awt.geom.AffineTransform;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

/**
 * @author pawel
 */
public class AffineTransformModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private final PropertyChangeSupport _pcs;
	
	private AffineTransform _transform;
	
	public static final String SHEAR_X = "shear_x";
	public static final String SHEAR_Y = "shear_y";
	public static final String TRANSLATE_X = "translate_x";
	public static final String TRANSLATE_Y = "translate_y";
	public static final String SCALE_X = "scale_x";
	public static final String SCALE_Y = "scale_y";
	public static final String TRANSFORM = "transform"; 
	
	public AffineTransformModel() {
		_pcs = new PropertyChangeSupport(this);
		_transform = new AffineTransform();
	}
	
	public double getScaleX() {
		return _transform.getScaleX();
	}
	
	public void setScaleX(double v) {
		if(_transform.getScaleX() != v) {
			double oldValue = _transform.getScaleX();
			_transform.setToScale(v, _transform.getScaleY());
			_pcs.firePropertyChange(new PropertyChangeEvent(this, SCALE_X, oldValue, v));
		}
	}
	
	public double getScaleY() {
		return _transform.getScaleY();
	}
	
	public void setScaleY(double v) {
		if(_transform.getScaleY() != v) {
			double oldValue = _transform.getScaleY();
			_transform.setToScale(_transform.getScaleX(), v);
			_pcs.firePropertyChange(new PropertyChangeEvent(this, SCALE_Y, oldValue, v));
		}
	}
	
	public double getTranslateX() {
		return _transform.getTranslateX();
	}
	
	public void setTranslateX(double v) {
		if(_transform.getTranslateX() != v) {
			double oldValue = _transform.getTranslateX();
			_transform.setToTranslation(v, _transform.getTranslateY());
			_pcs.firePropertyChange(new PropertyChangeEvent(this, TRANSLATE_X, oldValue, v));
		}
	}
	
	public double getTranslateY() {
		return _transform.getTranslateY();
	}
	
	public void setTranslateY(double v) {
		if(_transform.getTranslateY() != v) {
			double oldValue = _transform.getTranslateY();
			_transform.setToTranslation(_transform.getTranslateX(), v);
			_pcs.firePropertyChange(new PropertyChangeEvent(this, TRANSLATE_Y, oldValue, v));
		}
	}
	
	public double getShearX() {
		return _transform.getShearX();
	}
	
	public void setShearX(double v) {
		if(_transform.getShearX() != v) {
			double oldValue = _transform.getShearX();
			_transform.setToShear(v, _transform.getShearX());
			_pcs.firePropertyChange(new PropertyChangeEvent(this, SHEAR_X, oldValue, v));
		}
	}
	
	public double getShearY() {
		return _transform.getShearY();
	}
	
	public void setShearY(double v) {
		if(_transform.getShearY() != v) {
			double oldValue = _transform.getShearY();
			_transform.setToShear(_transform.getShearX(), v);
			_pcs.firePropertyChange(new PropertyChangeEvent(this, SHEAR_Y, oldValue, v));
		}
	}

	
	/**
	 * Returns instance of the AffineTransform class initialised
	 * with current parameter values
	 * 
	 * @return new instance of AffineTransform class
	 */
	public AffineTransform getTransform() {
		return new AffineTransform(_transform);
	}
	
	public void setTransform(AffineTransform transform) {
		if(_transform != transform) {
			AffineTransform oldValue = _transform;
			_transform = transform;
			_pcs.firePropertyChange(new PropertyChangeEvent(this, TRANSFORM, oldValue, transform));
		}
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		_pcs.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		_pcs.removePropertyChangeListener(listener);
	}
}
