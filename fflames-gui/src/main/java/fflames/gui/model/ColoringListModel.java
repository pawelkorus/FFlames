package fflames.gui.model;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

import fflames.base.coloring.ColoringFactory;
import fflames.base.IColoring;

import java.util.ArrayList;
import java.awt.Color;

public class ColoringListModel implements ListModel<String> {

	private final ArrayList<ListDataListener> _listeners;
	private final String[] _coloringStrategyNames = {"Black & White", "Linear Black & White", "Log Black & White", "Simple RGB", "Linear RGB", "Log RGB"};
	private final ColoringFactory _colorsFactory = new ColoringFactory();

	/**
	 * Creates a new instance of ColoringListModel
	 */
	public ColoringListModel() {
		this._listeners = new ArrayList<>();
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		_listeners.add(l);
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		_listeners.remove(l);
	}

	@Override
	public String getElementAt(int index) {
		return _coloringStrategyNames[index];
	}

	@Override
	public int getSize() {
		return _coloringStrategyNames.length;
	}

	public IColoring getColoring(int index, ArrayList<Color> parameters) {
		return _colorsFactory.getColoring(index, parameters);
	}
}
