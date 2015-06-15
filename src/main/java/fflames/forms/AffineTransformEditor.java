package fflames.forms;

import javax.swing.JPanel;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;

import javax.swing.border.EmptyBorder;

import fflames.model.AffineTransformModel;

public class AffineTransformEditor extends JPanel {
	private AffineTransformModel _model = new AffineTransformModel();
	private PropertyChangeListener _listener = new PropertyChangeListener();

	public AffineTransformEditor() {
		_model.addPropertyChangeListener(_listener);

		setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblA = new JLabel("A:");
		GridBagConstraints gbc_lblA = new GridBagConstraints();
		gbc_lblA.insets = new Insets(0, 0, 5, 5);
		gbc_lblA.anchor = GridBagConstraints.EAST;
		gbc_lblA.gridx = 0;
		gbc_lblA.gridy = 0;
		add(lblA, gbc_lblA);

		textField_A = new JTextField();
		GridBagConstraints gbc_textField_A = new GridBagConstraints();
		gbc_textField_A.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_A.insets = new Insets(0, 0, 5, 5);
		gbc_textField_A.gridx = 1;
		gbc_textField_A.gridy = 0;
		add(textField_A, gbc_textField_A);
		textField_A.setColumns(10);

		JLabel lblB = new JLabel("B:");
		GridBagConstraints gbc_lblB = new GridBagConstraints();
		gbc_lblB.insets = new Insets(0, 0, 5, 5);
		gbc_lblB.anchor = GridBagConstraints.EAST;
		gbc_lblB.gridx = 2;
		gbc_lblB.gridy = 0;
		add(lblB, gbc_lblB);

		textField_B = new JTextField();
		GridBagConstraints gbc_textField_B = new GridBagConstraints();
		gbc_textField_B.insets = new Insets(0, 0, 5, 5);
		gbc_textField_B.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_B.gridx = 3;
		gbc_textField_B.gridy = 0;
		add(textField_B, gbc_textField_B);
		textField_B.setColumns(10);

		JLabel lblE = new JLabel("E:");
		GridBagConstraints gbc_lblE = new GridBagConstraints();
		gbc_lblE.insets = new Insets(0, 0, 5, 5);
		gbc_lblE.anchor = GridBagConstraints.EAST;
		gbc_lblE.gridx = 4;
		gbc_lblE.gridy = 0;
		add(lblE, gbc_lblE);

		textField_C = new JTextField();
		GridBagConstraints gbc_textField_C = new GridBagConstraints();
		gbc_textField_C.insets = new Insets(0, 0, 5, 0);
		gbc_textField_C.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_C.gridx = 5;
		gbc_textField_C.gridy = 0;
		add(textField_C, gbc_textField_C);
		textField_C.setColumns(10);

		JLabel lblC = new JLabel("C:");
		GridBagConstraints gbc_lblC = new GridBagConstraints();
		gbc_lblC.insets = new Insets(0, 0, 0, 5);
		gbc_lblC.anchor = GridBagConstraints.EAST;
		gbc_lblC.gridx = 0;
		gbc_lblC.gridy = 1;
		add(lblC, gbc_lblC);

		textField_D = new JTextField();
		GridBagConstraints gbc_textField_D = new GridBagConstraints();
		gbc_textField_D.insets = new Insets(0, 0, 0, 5);
		gbc_textField_D.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_D.gridx = 1;
		gbc_textField_D.gridy = 1;
		add(textField_D, gbc_textField_D);
		textField_D.setColumns(10);

		JLabel lblD = new JLabel("D:");
		GridBagConstraints gbc_lblD = new GridBagConstraints();
		gbc_lblD.insets = new Insets(0, 0, 0, 5);
		gbc_lblD.anchor = GridBagConstraints.EAST;
		gbc_lblD.gridx = 2;
		gbc_lblD.gridy = 1;
		add(lblD, gbc_lblD);

		textField_E = new JTextField();
		GridBagConstraints gbc_textField_E = new GridBagConstraints();
		gbc_textField_E.insets = new Insets(0, 0, 0, 5);
		gbc_textField_E.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_E.gridx = 3;
		gbc_textField_E.gridy = 1;
		add(textField_E, gbc_textField_E);
		textField_E.setColumns(10);

		JLabel lblF = new JLabel("F:");
		GridBagConstraints gbc_lblF = new GridBagConstraints();
		gbc_lblF.anchor = GridBagConstraints.EAST;
		gbc_lblF.insets = new Insets(0, 0, 0, 5);
		gbc_lblF.gridx = 4;
		gbc_lblF.gridy = 1;
		add(lblF, gbc_lblF);

		textField_F = new JTextField();
		GridBagConstraints gbc_textField_F = new GridBagConstraints();
		gbc_textField_F.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_F.gridx = 5;
		gbc_textField_F.gridy = 1;
		add(textField_F, gbc_textField_F);
		textField_F.setColumns(10);

		_textFields[0] = textField_A;
		_textFields[1] = textField_B;
		_textFields[2] = textField_C;
		_textFields[3] = textField_D;
		_textFields[4] = textField_E;
		_textFields[5] = textField_F;
	}

	private static final long serialVersionUID = -18191441357596032L;
	private JTextField[] _textFields = new JTextField[6];
	private JTextField textField_A;
	private JTextField textField_B;
	private JTextField textField_C;
	private JTextField textField_D;
	private JTextField textField_E;
	private JTextField textField_F;

	public void reset() {
		for (JTextField field : _textFields) {
			field.setText(Double.valueOf(0).toString());
		}
	}

	public AffineTransformModel getModel() {
		return _model;
	}

	public void setModel(AffineTransformModel model) {
		_model.removePropertyChangeListener(_listener);
		_model = model;
		_model.addPropertyChangeListener(_listener);
	}

	class PropertyChangeListener implements java.beans.PropertyChangeListener {

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			switch (evt.getPropertyName()) {
			case AffineTransformModel.SCALE_X:
				textField_A.setText(evt.getNewValue().toString());
				break;
			case AffineTransformModel.SCALE_Y:
				textField_C.setText(evt.getNewValue().toString());
				break;
			case AffineTransformModel.SHEAR_X:
				textField_B.setText(evt.getNewValue().toString());
				break;
			case AffineTransformModel.SHEAR_Y:
				textField_D.setText(evt.getNewValue().toString());
				break;
			case AffineTransformModel.TRANSLATE_X:
				textField_E.setText(evt.getNewValue().toString());
				break;
			case AffineTransformModel.TRANSLATE_Y:
				textField_F.setText(evt.getNewValue().toString());
				break;
			case AffineTransformModel.TRANSFORM:
				AffineTransformModel model = (AffineTransformModel) evt.getSource();
				textField_A.setText(Double.toString(model.getScaleX()));
				textField_C.setText(Double.toString(model.getScaleY()));
				textField_B.setText(Double.toString(model.getShearX()));
				textField_D.setText(Double.toString(model.getShearY()));
				textField_E.setText(Double.toString(model.getTranslateX()));
				textField_F.setText(Double.toString(model.getTranslateY()));
				break;
			}
		}

	}
}