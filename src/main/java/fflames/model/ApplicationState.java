package fflames.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;

public class ApplicationState {
	private final PropertyChangeSupport _pcs;
	private HashMap<String, Object> params = new HashMap<String, Object>();	
	
	public static final String APPLICATION_NAME = "applicationName";
	public static final String LOADED_FRACTAL_FILE_PATH = "loadedFractalFile";
	
	public ApplicationState() {
		_pcs = new PropertyChangeSupport(this);
		setParam(APPLICATION_NAME, "FFlames");
	}
	
	public void setParam(String name, Object value) {
		Object oldValue = params.get(name);
		if(oldValue != value) {
			params.put(name, value);
			_pcs.firePropertyChange(name, oldValue, value);
		}
	}
	
	public Object getParam(String name) {
		return params.get(name);
	}
	
	public String getLoadedFractalFilePath() {
		return (String) params.get(LOADED_FRACTAL_FILE_PATH);
	}
	
	public String getApplicationName() {
		return (String) getParam(APPLICATION_NAME);
	}
	
	public boolean isFractalFileLoaded() {
		if(getParam(LOADED_FRACTAL_FILE_PATH) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		_pcs.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		_pcs.removePropertyChangeListener(listener);
	}
}
