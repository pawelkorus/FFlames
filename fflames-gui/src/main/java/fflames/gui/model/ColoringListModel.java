package fflames.gui.model;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

import fflames.generator.colouring.ColorsFactory;
import fflames.generator.IColour;

import java.util.ArrayList;
import java.util.Vector;
import java.awt.Color;

public class ColoringListModel implements ListModel<String> {

	private Vector<ListDataListener> listeners;
	private String[] lista = {"Black & White", "Linear Black & White", "Log Black & White", "Simple RGB", "Linear RGB", "Log RGB"};
	private ColorsFactory colorsFactory = new ColorsFactory();

	/**
	 * Creates a new instance of ColoringListModel
	 */
	public ColoringListModel() {
		listeners = new Vector<ListDataListener>();
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		listeners.add(l);
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		listeners.remove(l);
	}

	@Override
	public String getElementAt(int index) {
		return lista[index];
	}

	public int getColoursNumberAt(int index) {
		return colorsFactory.getColoring(index, null).getParametersQuantity();
	}

	@Override
	public int getSize() {
		return lista.length;
	}

	public IColour getColoring(int index, ArrayList<Color> parameters) {
		return colorsFactory.getColoring(index, parameters);
	}
}
