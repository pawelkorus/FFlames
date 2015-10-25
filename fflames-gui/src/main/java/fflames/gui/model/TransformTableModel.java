package fflames.gui.model;

import fflames.base.IVariation;
import fflames.base.Transform;
import fflames.gui.IVisitableModel;
import fflames.gui.IModelVisitor;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TransformTableModel extends AbstractTableModel 
	implements IVisitableModel {
	
	public TransformTableModel() {
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

	public ArrayList<IVariation> getVariationsAt(int index) {
		try {
			TransformsEntry entry = _transforms.get(index);
			return entry.transform.getVariations();
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			return null;
		}
	}

	public AffineTransform getAffineTransformAt(int index) {
		try {
			TransformsEntry entry = _transforms.get(index);
			return entry.transform.getAffineTr();
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Double getPropabilityAt(int index) {
		try {
			TransformsEntry entry = _transforms.get(index);
			return entry.propability;
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void add(double propability, Transform transform) {
		_transforms.add(TransformsEntry.create(propability, transform));
		fireTableRowsInserted(getRowCount() - 1, getRowCount());
	}

	public void addNew(AffineTransform _affineTr, ArrayList<IVariation> _variations, double pr) {
		Transform newTransform = new Transform(_affineTr, _variations);
		_transforms.add(TransformsEntry.create(pr, newTransform));
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

	private final List<TransformsEntry> _transforms = new ArrayList<>();
	private final String[] _columnNames = {"Propability", "Affine transform", "Variations"};
	private static final long serialVersionUID = -4510264602645148388L;

	@Override
	public void accept(IModelVisitor visitor) {
		visitor.handle(this);
	}
	
	private static class TransformsEntry {
		double propability;
		Transform transform;
		
		public static TransformsEntry create(double propability, Transform transform) {
			TransformsEntry entry = new TransformsEntry();
			entry.propability = propability;
			entry.transform = transform;
			return entry;
		}
	}
}
