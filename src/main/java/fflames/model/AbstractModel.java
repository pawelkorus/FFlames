package fflames.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.HashMap;

public abstract class AbstractModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private final PropertyChangeSupport _pcs;
	private HashMap<String, Object> _params = new HashMap<String, Object>();
	
	public AbstractModel() {
		_pcs = new PropertyChangeSupport(this);
	}
	
	public void setParam(String name, Object value) {
		Object oldValue = _params.get(name);
		if(oldValue != value) {
			_params.put(name, value);
			_pcs.firePropertyChange(name, oldValue, value);
		}
	}

	public Object getParam(String name) {
		return getParam(name, null);
	}
	
	public Object getParam(String name, Object defaultValue) {
		Object value = _params.get(name);
		if(value == null) {
			return defaultValue;
		} else {
			return value;
		}
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		_pcs.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		_pcs.removePropertyChangeListener(listener);
	}

}
