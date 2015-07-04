package fflames.gui.model;

import fflames.base.IVariation;
import fflames.base.Transform;
import fflames.gui.IVisitableModel;
import fflames.gui.IModelVisitor;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class TransformTableModel extends AbstractTableModel 
	implements IVisitableModel {
	
	public TransformTableModel() {
		 _transforms = new ArrayList<>();
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

	private ArrayList<Transform> _transforms;
	private final String[] _columnNames = {"Propability", "Affine transform", "Variations"};
	private static final long serialVersionUID = -4510264602645148388L;

	@Override
	public void accept(IModelVisitor visitor) {
		visitor.handle(this);
	}
}
