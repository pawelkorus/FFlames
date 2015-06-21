/*
 * VariationsTableModel.java
 *
 * Created on March 18, 2008, 1:11 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package fflames.gui.model;

import javax.swing.table.*;

import fflames.base.IVariation;
import fflames.base.variation.VariationsFactory;

import java.util.ArrayList;

/**
 *
 * @author victories
 */
public class VariationsTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -8168467956040246878L;

	private ArrayList<ArrayList<Double>> _parameters = new ArrayList<>();
	private ArrayList<Double> _coefficients = new ArrayList<Double>();
	private int _columns;
	private int _rows;

	/**
	 * Creates a new instance of VariationsTableModel
	 */
	public VariationsTableModel() {
		_rows = VariationsFactory.getVariationQuantity();

		int maxAdditionalParams = 0;
		_parameters.ensureCapacity(_rows);
		_coefficients.ensureCapacity(_rows);
		for (int i = 0; i < _rows; i++) {
			_coefficients.add(Double.valueOf(0));

			IVariation tmp = VariationsFactory.getVariation(i, Double.valueOf(0));
			int parametersQuantity = tmp.getParametersQuantity();

			ArrayList<Double> parameters = new ArrayList<>(parametersQuantity);
			for (int j = 0; j < parametersQuantity; j++) {
				parameters.add(Double.valueOf(0));
			}
			_parameters.add(parameters);

			if (maxAdditionalParams < parametersQuantity) {
				maxAdditionalParams = parametersQuantity;
			}
		}

		_columns = 2 + maxAdditionalParams;
	}

	@Override
	public int getColumnCount() {
		return _columns;
	}

	@Override
	public int getRowCount() {
		return _rows;
	}

	@Override
	public String getColumnName(int index) {
		if (index < 0) {
			return "";
		} else if (index == 0) {
			return "Name";
		} else if (index == 1) {
			return "Coefficient";
		} else {
			return "Param";
		}
	}

	@Override
	public Object getValueAt(int row, int col) {
		if (row < 0 || row >= _rows || col < 0 || col >= _columns) {
			return null;
		} else {
			if (col == 0) {
				return VariationsFactory.getVariationName(row);
			} else if (col == 1) {
				return _coefficients.get(row);
			} else {
				int paramIndex = col - 2;
				ArrayList<Double> additionalParams = _parameters.get(row);
				if (paramIndex < additionalParams.size()) {
					return additionalParams.get(paramIndex);
				} else {
					return "";
				}
			}

		}
	}

	public ArrayList<Double> getParameters(int row) {
		if (row < 0 || row >= _parameters.size()) {
			return new ArrayList<>();
		}

		return _parameters.get(row);
	}

	/**
	 * Returns IVariation object corresponding to the given row.
	 *
	 * @param row row index
	 * @return Variation object
	 */
	public IVariation getWariation(int row) {
		IVariation temp = VariationsFactory.getVariation(row, _coefficients.get(row));
		if (temp.getParametersQuantity() > 0) {
			ArrayList<Double> parTemp = new ArrayList<>(_parameters.get(row));
			temp.setParameters(parTemp);
		}
		return temp;
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		if (row < 0 || row >= _rows || col < 0 || col >= _columns) {
			return;
		}

		if (col == 1) {
			_coefficients.set(row, Double.valueOf(value.toString()));
		} else {
			int paramIndex = col - 2;
			ArrayList<Double> additionalParams = _parameters.get(row);
			if (paramIndex < additionalParams.size()) {
				additionalParams.set(paramIndex, Double.valueOf(value.toString()));
			}
		}
		fireTableCellUpdated(row, col);
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		if (row < 0 || row >= _rows || col < 0 || col >= _columns) {
			return false;
		}

		if (col == 0) {
			return false;
		} else if (col == 1) {
			return true;
		} else {
			int paramsCol = col - 2;
			return paramsCol < _parameters.get(row).size();
		}
	}

	/**
	 * Resets variation coefficient and all additional parameters to 0
	 */
	public void clearParameters() {
		for (int i = 0; i < _rows; i++) {
			_coefficients.set(i, Double.valueOf(0));
			ArrayList<Double> parameters = _parameters.get(i);
			int parametersNum = parameters.size();
			for (int j = 0; j < parametersNum; j++) {
				parameters.set(j, Double.valueOf(0));
			}
		}
	}

	public void reset() {
		clearParameters();
		fireTableDataChanged();
	}
}
