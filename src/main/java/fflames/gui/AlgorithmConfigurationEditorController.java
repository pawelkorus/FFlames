package fflames.gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import fflames.gui.forms.AlgorithmConfigurationEditor;
import fflames.gui.model.AlgorithmConfigurationModel;

public class AlgorithmConfigurationEditorController implements PropertyChangeListener {
	private AlgorithmConfigurationModel _model;
	
	public AlgorithmConfigurationEditorController(AlgorithmConfigurationModel model) {
		_model = model;
	}
	
	public void rotationsNumberChanged(int value) {
		_model.setRotationsNumber(value);
	}

	public void iterationsNumberChanged(int value) {
		_model.setItarationsNumber(value);
	}

	public void imageWidthChanged(int value) {
		_model.setImageWidth(value);
	}

	public void imageHeightChanged(int value) {
		_model.setImageWidth(value);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String propertyName = evt.getPropertyName();
		
		if(AlgorithmConfigurationEditor.Property.RotationsNumber.equals(propertyName)) {
			rotationsNumberChanged((int) evt.getNewValue());
		} else if(AlgorithmConfigurationEditor.Property.IterationsNumber.equals(propertyName)) {
			iterationsNumberChanged((int) evt.getNewValue());
		} else if(AlgorithmConfigurationEditor.Property.ImageWidth.equals(propertyName)) {
			imageWidthChanged((int) evt.getNewValue());
		} else if(AlgorithmConfigurationEditor.Property.ImageHeight.equals(propertyName)) {
			imageHeightChanged((int) evt.getNewValue());
		} else if(AlgorithmConfigurationEditor.Property.SuperSampling.equals(propertyName)) {
			_model.setSuperSampling((int) evt.getNewValue());
		}
	}

}
