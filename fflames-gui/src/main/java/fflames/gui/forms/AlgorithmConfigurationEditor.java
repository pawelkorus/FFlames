package fflames.gui.forms;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import fflames.gui.FormActions;
import fflames.gui.model.AlgorithmConfigurationModel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class AlgorithmConfigurationEditor extends JPanel {
	public PropertyChangeSupport propertyChange;
	
	public enum Property {
		RotationsNumber("rotationsNumber"),
		IterationsNumber("iterationsNumber"),
		ImageWidth("imageWidth"),
		ImageHeight("imageHeight"),
		SuperSampling("superSampling");
		
		private final String _name;
		
		private Property(String name) {
			_name = name;
		}
		
		public String toString() {
			return _name;
		}
		
		public boolean equals(String value) {
			return _name.equals(value);
		}
	}
	
	public AlgorithmConfigurationEditor() {
		propertyChange = new PropertyChangeSupport(this);
		
		_model = new AlgorithmConfigurationModel();
		_listener = new ModelChangeListener();
		_actions = new FormActions(this);
		initGUI();
		_focusListener = new TextFieldFocusListener();
		_tfRotations.addFocusListener(_focusListener);
		_tfIterationsNumber.addFocusListener(_focusListener);
		_tfImageWidth.addFocusListener(_focusListener);
		_tfImageHeight.addFocusListener(_focusListener);
		
		_cbSuperSampling.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				_actions.firePropertyChange(Property.SuperSampling.toString(), _model.getSuperSampling(), Integer.parseInt((String) _cbSuperSampling.getSelectedItem()));
			}
			
		});
	}

	public AlgorithmConfigurationModel getModel() {
		return _model;
	}
	
	public void setModel(AlgorithmConfigurationModel model) {
		_model.removePropertyChangeListener(_listener);
		_model = model;
		initValuesFromModel();
		_model.addPropertyChangeListener(_listener);		
	}
	
	public FormActions getActions() {
		return _actions;
	}
	
	private void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 116, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lblIterationsNumber = new JLabel("Iterations number:");
		GridBagConstraints gbc_lblIterationsNumber = new GridBagConstraints();
		gbc_lblIterationsNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblIterationsNumber.anchor = GridBagConstraints.EAST;
		gbc_lblIterationsNumber.gridx = 0;
		gbc_lblIterationsNumber.gridy = 0;
		add(lblIterationsNumber, gbc_lblIterationsNumber);
		
		_tfIterationsNumber = new JTextField();
		GridBagConstraints gbc__tfIterationsNumber = new GridBagConstraints();
		gbc__tfIterationsNumber.gridwidth = 3;
		gbc__tfIterationsNumber.insets = new Insets(0, 0, 5, 0);
		gbc__tfIterationsNumber.fill = GridBagConstraints.HORIZONTAL;
		gbc__tfIterationsNumber.gridx = 1;
		gbc__tfIterationsNumber.gridy = 0;
		add(_tfIterationsNumber, gbc__tfIterationsNumber);
		_tfIterationsNumber.setColumns(10);
		
		lblOutputImageSize = new JLabel("Output image size:");
		GridBagConstraints gbc_lblOutputImageSize = new GridBagConstraints();
		gbc_lblOutputImageSize.anchor = GridBagConstraints.EAST;
		gbc_lblOutputImageSize.insets = new Insets(0, 0, 5, 5);
		gbc_lblOutputImageSize.gridx = 0;
		gbc_lblOutputImageSize.gridy = 1;
		add(lblOutputImageSize, gbc_lblOutputImageSize);
		
		_tfImageWidth = new JTextField();
		GridBagConstraints gbc__tfImageWidth = new GridBagConstraints();
		gbc__tfImageWidth.insets = new Insets(0, 0, 5, 5);
		gbc__tfImageWidth.fill = GridBagConstraints.HORIZONTAL;
		gbc__tfImageWidth.gridx = 1;
		gbc__tfImageWidth.gridy = 1;
		add(_tfImageWidth, gbc__tfImageWidth);
		_tfImageWidth.setColumns(10);
		
		lblX = new JLabel("x");
		GridBagConstraints gbc_lblX = new GridBagConstraints();
		gbc_lblX.insets = new Insets(0, 0, 5, 5);
		gbc_lblX.gridx = 2;
		gbc_lblX.gridy = 1;
		add(lblX, gbc_lblX);
		
		_tfImageHeight = new JTextField();
		GridBagConstraints gbc__tfImageHeight = new GridBagConstraints();
		gbc__tfImageHeight.insets = new Insets(0, 0, 5, 0);
		gbc__tfImageHeight.fill = GridBagConstraints.HORIZONTAL;
		gbc__tfImageHeight.gridx = 3;
		gbc__tfImageHeight.gridy = 1;
		add(_tfImageHeight, gbc__tfImageHeight);
		_tfImageHeight.setColumns(10);
		
		lblRotations = new JLabel("Rotations:");
		GridBagConstraints gbc_lblRotations = new GridBagConstraints();
		gbc_lblRotations.anchor = GridBagConstraints.EAST;
		gbc_lblRotations.insets = new Insets(0, 0, 5, 5);
		gbc_lblRotations.gridx = 0;
		gbc_lblRotations.gridy = 2;
		add(lblRotations, gbc_lblRotations);
		
		_tfRotations = new JTextField();
		GridBagConstraints gbc__tfRotations = new GridBagConstraints();
		gbc__tfRotations.insets = new Insets(0, 0, 5, 0);
		gbc__tfRotations.gridwidth = 3;
		gbc__tfRotations.fill = GridBagConstraints.HORIZONTAL;
		gbc__tfRotations.gridx = 1;
		gbc__tfRotations.gridy = 2;
		add(_tfRotations, gbc__tfRotations);
		_tfRotations.setColumns(10);
		
		lblSuperSampling = new JLabel("Super sampling:");
		GridBagConstraints gbc_lblSuperSampling = new GridBagConstraints();
		gbc_lblSuperSampling.anchor = GridBagConstraints.EAST;
		gbc_lblSuperSampling.insets = new Insets(0, 0, 0, 5);
		gbc_lblSuperSampling.gridx = 0;
		gbc_lblSuperSampling.gridy = 3;
		add(lblSuperSampling, gbc_lblSuperSampling);
		
		_cbSuperSampling = new JComboBox<String>();
		_cbSuperSampling.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16"}));
		GridBagConstraints gbc__cbSuperSampling = new GridBagConstraints();
		gbc__cbSuperSampling.insets = new Insets(0, 0, 0, 5);
		gbc__cbSuperSampling.fill = GridBagConstraints.HORIZONTAL;
		gbc__cbSuperSampling.gridx = 1;
		gbc__cbSuperSampling.gridy = 3;
		add(_cbSuperSampling, gbc__cbSuperSampling);
	}
	
	private void initValuesFromModel() {
		_tfIterationsNumber.setText(String.valueOf(_model.getIterationsNumber()));
		_tfImageWidth.setText(String.valueOf(_model.getImageWidth()));
		_tfImageHeight.setText(String.valueOf(_model.getImageHeight()));
		_tfRotations.setText(String.valueOf(_model.getRotationsNumber()));
	}
	
	private class ModelChangeListener implements PropertyChangeListener {

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			String propertyName = evt.getPropertyName();
			switch(propertyName) {
			case AlgorithmConfigurationModel.ITERATIONS_NUMBER:
				_tfIterationsNumber.setText(String.valueOf(_model.getIterationsNumber()));
			case AlgorithmConfigurationModel.IMAGE_WIDTH:
				_tfImageWidth.setText(String.valueOf(_model.getImageWidth()));
			case AlgorithmConfigurationModel.IMAGE_HEIGHT:
				_tfImageWidth.setText(String.valueOf(_model.getImageHeight()));
			case AlgorithmConfigurationModel.ROTATIONS_NUMBER:
				_tfRotations.setText(String.valueOf(_model.getRotationsNumber()));
			case AlgorithmConfigurationModel.SUPER_SAMPLING:
				_cbSuperSampling.setSelectedItem(String.valueOf(_model.getSuperSampling()));
			}
		}
		
	}
	
	private class TextFieldFocusListener implements FocusListener {

		@Override
		public void focusGained(FocusEvent e) {
		}

		@Override
		public void focusLost(FocusEvent e) {
			Object source = e.getSource();
			if(source == _tfRotations) {
				_actions.firePropertyChange(Property.RotationsNumber.toString(), _model.getRotationsNumber(), Integer.parseInt(_tfRotations.getText()));
			} else if(source == _tfIterationsNumber) {
				_actions.firePropertyChange(Property.IterationsNumber.toString(), _model.getIterationsNumber(), Integer.parseInt(_tfIterationsNumber.getText()));
			} else if(source == _tfImageWidth) {
				_actions.firePropertyChange(Property.ImageWidth.toString(), _model.getImageWidth(), Integer.parseInt(_tfImageWidth.getText()));
			} else if(source == _tfImageHeight) {
				_actions.firePropertyChange(Property.ImageHeight.toString(), _model.getImageHeight(), Integer.parseInt(_tfImageHeight.getText()));
			}
		}

	}
	
	private static final long serialVersionUID = 1L;
	private JLabel lblIterationsNumber;
	private JLabel lblOutputImageSize;
	private JTextField _tfImageWidth;
	private JLabel lblX;
	private JLabel lblRotations;
	private JTextField _tfRotations;
	private JTextField _tfIterationsNumber;
	private JTextField _tfImageHeight;
	private AlgorithmConfigurationModel _model;
	private ModelChangeListener _listener;
	//private AlgorithmConfigurationEditorActions _actions;
	private TextFieldFocusListener _focusListener;
	private FormActions _actions;
	private JLabel lblSuperSampling;
	private JComboBox<String> _cbSuperSampling;
}
