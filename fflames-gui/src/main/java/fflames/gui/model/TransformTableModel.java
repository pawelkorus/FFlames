package fflames.gui.model;

import fflames.base.Transform;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import fflames.base.IVariation;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TransformTableModel extends AbstractTableModel {

	public static final String CURRENT_TRANSFORM_INDEX = 
			"current_transform_index";
	
	public TransformTableModel() {
		 _transforms = new ArrayList<>();
		 _pcs = new PropertyChangeSupport(this);
		 _currentTransformIndex = -1;
	}
	
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

	public void reset() {
		int size = _transforms.size();
		
		if(size > 0) {
			_transforms.clear();
			fireTableRowsDeleted(0, size - 1);
		}
	}
	
	public int getCurrentTransformIndex() {
		return _currentTransformIndex;
	}
	
	public void setCurrentTransformIndex(int index) {
		if( index < 0 || index >= getRowCount()) {
			return;
		}
		
		if(index == _currentTransformIndex) {
			return;
		}
		
		int oldValue = _currentTransformIndex;
		_currentTransformIndex = index;
		
		_pcs.firePropertyChange(
				CURRENT_TRANSFORM_INDEX, oldValue, _currentTransformIndex);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		_pcs.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		_pcs.removePropertyChangeListener(listener);
	}

	private int _currentTransformIndex;
	private ArrayList<Transform> _transforms;
	private final PropertyChangeSupport _pcs;
	private final String[] _columnNames = {"Propability", "Affine transform", "Variations"};
	private static final long serialVersionUID = -4510264602645148388L;
}
