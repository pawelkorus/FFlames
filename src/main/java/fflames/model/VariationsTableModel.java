/*
 * VariationsTableModel.java
 *
 * Created on March 18, 2008, 1:11 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fflames.model;

import javax.swing.table.*;

import fflames.generator.IVariation;
import fflames.generator.variation.VariationsFactory;

import java.util.ArrayList;
import java.util.Vector;

/**
 * 
 * @author victories
 */
public class VariationsTableModel extends AbstractTableModel {
	private static final long serialVersionUID = -8168467956040246878L;

	private ArrayList<Vector<Double>> _parameters = new ArrayList<Vector<Double>>();
	private ArrayList<Double> _coefficients = new ArrayList<Double>();
	private int _columns;
	private int _rows;

	/**
	 * Creates a new instance of VariationsTableModel
	 */
	public VariationsTableModel() {
		_rows = VariationsFactory.getWariationQuantity();

		int maxAdditionalParams = 0;
		_parameters.ensureCapacity(_rows);
		_coefficients.ensureCapacity(_rows);
		for (int i = 0; i < _rows; i++) {
			_coefficients.add(Double.valueOf(0));

			IVariation tmp = VariationsFactory.getWariation(i, Double.valueOf(0));
			int parametersQuantity = tmp.getParametersQuantity();

			Vector<Double> parameters = new Vector<Double>(parametersQuantity);
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

	public int getColumnCount() {
		return _columns;
	}

	public int getRowCount() {
		return _rows;
	}

	@Override
	public String getColumnName(int index) {
		if (index < 0)
			return "";
		else if (index == 0)
			return "Name";
		else if (index == 1)
			return "Coefficient";
		else
			return "Param";
	}

	public Object getValueAt(int row, int col) {
		if (row < 0 || row >= _rows || col < 0 || col >= _columns) {
			return null;
		} else {
			if (col == 0)
				return VariationsFactory.getWariationName(row);
			else if (col == 1)
				return _coefficients.get(row);
			else {
				int paramIndex = col - 2;
				Vector<Double> additionalParams = _parameters.get(row);
				if (paramIndex < additionalParams.size()) {
					return additionalParams.get(paramIndex);
				} else {
					return "";
				}
			}

		}
	}

	public Vector<Double> getParameters(int row) {
		if (row < 0 || row >= _parameters.size())
			return new Vector<Double>();

		return _parameters.get(row);
	}

	/**
	 * Returns IVariation object corresponding to the given row.
	 * 
	 * @param row
	 *            row index
	 * @return Variation object
	 */
	public IVariation getWariation(int row) {
		IVariation temp = VariationsFactory.getWariation(row, _coefficients.get(row));
		if (temp.getParametersQuantity() > 0) {
			Vector<Double> parTemp = new Vector<Double>(_parameters.get(row));
			temp.setParameters(parTemp);
		}
		return temp;
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		if (row < 0 || row >= _rows || col < 0 || col >= _columns) {
			return;
		}

		if (col == 1)
			_coefficients.set(row, Double.valueOf(value.toString()));
		else {
			int paramIndex = col - 2;
			Vector<Double> additionalParams = _parameters.get(row);
			if (paramIndex < additionalParams.size()) {
				additionalParams.set(paramIndex, Double.valueOf(value.toString()));
			}
		}
		fireTableCellUpdated(row, col);
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		if (row < 0 || row >= _rows || col < 0 || col >= _columns)
			return false;

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
			Vector<Double> parameters = _parameters.get(i);
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
