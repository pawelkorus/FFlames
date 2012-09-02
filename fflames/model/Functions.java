/*
 * Functions.java
 *
 * Created on March 7, 2008, 1:51 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fflames.model;

import java.awt.geom.AffineTransform;
import java.io.IOException;
import javax.swing.ListModel;
import java.util.Vector;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListDataEvent;

import fflames.variation.Linear;

import java.awt.geom.Point2D;

/**
 * 
 * @author victories
 */
public class Functions implements ListModel {
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
		setPropabilities();
		for (int i = 0; i < listeners.size(); i++) {
			listeners.get(i).contentsChanged(
					new ListDataEvent(object, type, 0, index));
		}
	}

	/**
	 * Funkcja obliczajaca wektor prawdopodobieństw z uwzględnieniem
	 * przekształceń symetrycznych.
	 */
	private void setPropabilities() {
		propabilities.removeAllElements();

		double groupPr = 1.0 / (rotations.size() + 1.0);

		for (int i = 0; i < functions.size(); i++)
			propabilities.add(functions.get(i).getPropability() * groupPr);
		for (int i = 0; i < rotations.size(); i++)
			propabilities.add(groupPr);
	}

	/**
	 * Fukcja sprawdzaj�ca poprawno�� wprowadzonych i obliczonych
	 * prawdopodobie?stw.
	 * 
	 * @return -1 - suma prawdopodobie�stw mniejsza ni� 1 0 - suma
	 *         prawdopodobie�stw r�wna 1 1 - suma prawdopodobie�stw wi�ksza ni�
	 *         1
	 */
	public int checkPropabilities() {
		Double pr = new Double(0.0);
		// Po��czenie transformacji i przekszta�ce� symetrycznych
		Vector<Transform> temp = new Vector<Transform>(functions);
		temp.addAll(rotations);

		for (int i = 0; i < temp.size() - 1; i++) {
			pr += propabilities.get(i);
		}
		if (pr + propabilities.lastElement() < 1.0) {
			propabilities.set(propabilities.size() - 1, 1.0 - pr);
			return -1;
		} else if (pr + propabilities.lastElement() > 1.0)
			return 1;
		return 0;
	}

	public void addListDataListener(ListDataListener l) {
		listeners.add(l);
	}

	public void removeListDataListener(ListDataListener l) {
		listeners.remove(l);
	}

	public String getElementAt(int index) {
		return functions.get(index).toString()
				+ propabilities.get(index).toString();
	}

	public int getSize() {
		return functions.size();
	}

	/**
	 * 
	 * @return Ilo?? funkcji, bez funkcji obracaj?cych
	 */
	public int getFunctionsQuantity() {
		return functions.size();
	}

	public void addElement(Transform transform) {
		functions.add(transform);
		fireContentsChanged(this, ListDataEvent.CONTENTS_CHANGED, getSize());
	}

	public void removeElement(int index) {
		functions.remove(index);
		fireContentsChanged(this, ListDataEvent.CONTENTS_CHANGED, getSize());
	}

	public void removeAllElemens() {
		functions.removeAllElements();
		rotations.removeAllElements();
		fireContentsChanged(this, ListDataEvent.CONTENTS_CHANGED, getSize());
	}

	/**
	 * Fnkcja zwracj?ca prawdopodobienstwo funkcji dla funkcji o numerze index z
	 * uwzgl?dnieniem funkcji obracaj?cych
	 * 
	 * @param index
	 * @return warto?? double z przedzia?u 0 - 1
	 */
	public Double getPr(int index) {
		return propabilities.get(index);
	}

	public Transform getTransformAt(int index) {
		return functions.get(index);
	}

	/**
	 * Oblicza nowe wsp??rz?dne punktu, wed?ug zadanej funkcji
	 * 
	 * @param index
	 *            - numer funkcji u?ywanej do obliczenia nowego punktu
	 * @param point
	 *            - transformowany punkt
	 * @return true - je?li funkcja jest zwyk?? funkcj? false - je?li funkcja
	 *         jest funkcj? obracaj?c?
	 */
	public boolean oblicz(int index, Point2D point) {
		if (index > functions.size() - 1) {
			rotations.get(index - functions.size()).oblicz(point);
			return false;
		} else {
			functions.get(index).oblicz(point);
			return true;
		}
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

	public void addRotations(int rotQuantity) {
		rotations.removeAllElements();

		// Obliczenie kata w radianach
		double angle = Math.toRadians(360.0 / (rotQuantity + 1.0));
		for (int i = 0; i < rotQuantity; i++) {
			rotations.add(new Transform(new AffineTransform(Math.cos(angle),
					-Math.sin(angle), Math.sin(angle), Math.cos(angle), 0, 0),
					new Linear(1.0), new Double(1.0)));
			angle += angle;
		}

		fireContentsChanged(this, ListDataEvent.CONTENTS_CHANGED, getSize());
	}
}
