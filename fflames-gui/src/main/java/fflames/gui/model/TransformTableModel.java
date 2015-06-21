package fflames.gui.model;

import fflames.base.Transform;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import fflames.base.IVariation;

public class TransformTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -4510264602645148388L;

	@Override
	public int getRowCount() {
		return _transforms.size();
	}

	@Override
	public int getColumnCount() {
		return _columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// Transform transform = _transforms.get(rowIndex);
		switch (columnIndex) {
			case 0:
				return getPropabilityAt(rowIndex);
			case 1:
				return getAffineTransformAt(rowIndex);
			case 2:
				return getVariationsAt(rowIndex);
			default:
				return null;
		}
	}

	public void setTransforms(ArrayList<Transform> transforms) {
		_transforms = transforms;
		fireTableDataChanged();
	}

	public ArrayList<Transform> getTransforms() {
		return _transforms;
	}

	public ArrayList<IVariation> getVariationsAt(int index) {
		try {
			Transform transform = _transforms.get(index);
			return transform.getVariations();
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			return null;
		}
	}

	public AffineTransform getAffineTransformAt(int index) {
		try {
			Transform transform = _transforms.get(index);
			return transform.getAffineTr();
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Double getPropabilityAt(int index) {
		try {
			Transform transform = _transforms.get(index);
			return transform.getPropability();
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void add(Transform transform) {
		_transforms.add(transform);
		fireTableRowsInserted(getRowCount() - 1, getRowCount());
	}

	public void addNew(AffineTransform _affineTr, ArrayList<IVariation> _variations, Double pr) {
		Transform newTransform = new Transform(_affineTr, _variations, pr);
		_transforms.add(newTransform);
	}

	public void remove(int row) {
		try {
			_transforms.remove(row);
			fireTableRowsDeleted(row, row);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	public void remove(int[] rows) {
		if (rows.length == 0) {
			return;
		}

		int row = rows[0];
		for (int i = 0; i < rows.length; i++) {
			remove(row);
		}
	}

	public void reset() {
		int size = _transforms.size();
		_transforms.clear();
		fireTableRowsDeleted(0, size - 1);
	}

	private ArrayList<Transform> _transforms = new ArrayList<Transform>();
	private String[] _columnNames = {"Propability", "Affine transform", "Variations"};
}
