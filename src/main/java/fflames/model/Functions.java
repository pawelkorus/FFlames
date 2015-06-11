/*
 * Functions.java
 *
 * Created on March 7, 2008, 1:51 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fflames.model;

import java.io.IOException;
import javax.swing.ListModel;

import java.util.Vector;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListDataEvent;

/**
 * 
 * @author victories
 */
public class Functions implements ListModel<String> {
	private Vector<ListDataListener> listeners = new Vector<ListDataListener>();
	private Vector<Transform> functions = new Vector<Transform>();
	private Vector<Transform> rotations = new Vector<Transform>();
	private Vector<Double> propabilities = new Vector<Double>();

	/**
	 * Creates a new instance of Functions
	 */
	public Functions() {
	}

	private void fireContentsChanged(Object object, int type, int index) {
		for (int i = 0; i < listeners.size(); i++) {
			listeners.get(i).contentsChanged(new ListDataEvent(object, type, 0, index));
		}
	}

	public void addListDataListener(ListDataListener l) {
		listeners.add(l);
	}

	public void removeListDataListener(ListDataListener l) {
		listeners.remove(l);
	}

	/**
	 * @override
	 */
	public String getElementAt(int index) {
		return functions.get(index).toString() + propabilities.get(index).toString();
	}

	public int getSize() {
		return functions.size();
	}

	public void addElement(Transform transform) {
		functions.add(transform);
		propabilities.add(transform.getPropability());
		fireContentsChanged(this, ListDataEvent.CONTENTS_CHANGED, getSize());
	}

	public void removeElement(int index) {
		functions.remove(index);
		propabilities.remove(index);
		fireContentsChanged(this, ListDataEvent.CONTENTS_CHANGED, getSize());
	}

	public void removeAllElemens() {
		functions.removeAllElements();
		propabilities.removeAllElements();
		rotations.removeAllElements();
		fireContentsChanged(this, ListDataEvent.CONTENTS_CHANGED, getSize());
	}

	public Transform getTransformAt(int index) {
		return functions.get(index);
	}

	/**
	 * Funkcja zapisuj?ca informacje o transformacjach do obiektu zapisuj?cego
	 * 
	 * @param out
	 *            obiekt zapisuj?cy
	 */
	public void writeXML(java.io.OutputStreamWriter out) {
		try {
			out.write("<Functions>\r\n");
			for (int i = 0; i < functions.size(); i++) {
				out.write("<Function>\r\n");
				functions.get(i).writeXML(out);
				out.write("</Function>\r\n");
			}
			out.write("</Functions>\r\n");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void reset() {
		removeAllElemens();
	}
}
