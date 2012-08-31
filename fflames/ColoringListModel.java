/*
 * ColoringListModel.java
 *
 * Created on March 15, 2008, 2:29 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fflames;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

import java.util.Vector;
import java.awt.Color;
/**
 *
 * @author victories
 */
public class ColoringListModel implements ListModel {
    private Vector<ListDataListener> listeners;
    private String[] lista = {"Black & White", "Linear Black & White", 
                              "Log Black & White", "Linear Coloring"};
    private ColorsFactory colorsFactory = new ColorsFactory();
    
    /** Creates a new instance of ColoringListModel */
    public ColoringListModel() {
        listeners = new Vector<ListDataListener>();
    }
    
    public void addListDataListener(ListDataListener l) { listeners.add(l); }
    public void removeListDataListener(ListDataListener l) { listeners.remove(l); }
    public String getElementAt(int index) { return lista[index]; }
    public int getSize() { return lista.length; }
    
    public IKolor getColoring(int index, Vector<Color> parameters) {
        return colorsFactory.getColoring(index, parameters);
    }
}
