package fflames.gui.forms;

import fflames.gui.model.AbstractModel;
import fflames.gui.model.ProgressModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JProgressBar;

public class ProgressBar extends JProgressBar {
	public ProgressBar() {
		super();
		_model = null;
		_listener = new ModelChangeListener();
	}
	
	public void setModel(AbstractModel model) {
		if(_model != null) {
			_model.removePropertyChangeListener(_listener);
		}
		_model = model;
		model.addPropertyChangeListener(_listener);
				
		setMinimum((Integer)model.getParam(ProgressModel.START_PROGRESS_VALUE));
		setMaximum((Integer)model.getParam(ProgressModel.END_PROGRESS_VALUE));
	}

	private class ModelChangeListener implements PropertyChangeListener {

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			String propertyName = evt.getPropertyName();
			switch(propertyName) {
				case ProgressModel.PROGRESS: {
					int value = (Integer) evt.getNewValue();
					setValue(value);
				} break;
				case ProgressModel.START_PROGRESS_VALUE: {
					int value = (Integer) evt.getNewValue();
					setMinimum(value);
				} break;
				case ProgressModel.END_PROGRESS_VALUE: {
					int value = (Integer) evt.getNewValue();
					setMaximum(value);
				} break;
			}
		}
		
	}
	
	private ModelChangeListener _listener;
	private AbstractModel _model;
}
