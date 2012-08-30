/*
 * WariationsTableModel.java
 *
 * Created on March 18, 2008, 1:11 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fflames;

import javax.swing.table.*;

import fflames.variation.*;

import java.util.Vector;
import java.util.Hashtable;
/**
 *
 * @author victories
 */
public class WariationsTableModel extends AbstractTableModel {
    private String[] columnNames = {"Wariacja", "Wsp�czynnik"};
    private Hashtable<Integer, Vector<Double>> parameters = new Hashtable<Integer, Vector<Double>>();
    /**
     * Creates a new instance of WariationsTableModel
     */
    public WariationsTableModel() {
        for(int i=0; i < WariationsFactory.getWariationQuantity(); i++) {
            parameters.put(new Integer(i), new Vector<Double>()); 
            parameters.get(i).add(0.0);
        }
    }
   
    public int getColumnCount() {
        return columnNames.length;
    }
    
    public int getRowCount() {
        return parameters.keySet().size();
    }
    
    @Override public String getColumnName(int col) {
        return columnNames[col];
    }
   
    /******************************************************************************/
    /* TO DO: W przypadku gdy wariacja ma wiecej parametrow nie sa inicjalizowane */
    /******************************************************************************/
    public Object getValueAt(int row, int col) {
        if(col == 0)
            return WariationsFactory.getWariationName(row);
        else return parameters.get(row).get(0);
    }

    public Vector<Double> getParameters(int row) {
        Vector<Double> temp = new Vector<Double>();
        temp = parameters.get(row);
        temp.remove(temp.firstElement());
        return temp;
    }
    
    /**
     * Funkcja zwraca Wariacja odpowiadaj�c� danemu wierszowi w tabeli
     * z odpowiedaj�c� mu warto�ci� wsp�czynnika.
     * @param row numer wiersza
     * @return Obiekt typu IWariation
     */
    public IWariation getWariation(int row) {
        IWariation temp = WariationsFactory.getWariation(row, parameters.get(row).firstElement());
        if( temp.getParametersQuantity() > 0 ) {
            Vector<Double> parTemp = new Vector<Double>(parameters.get(row));
            parTemp.remove(0);
            temp.setParameters(parTemp);
        }
        return temp;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public void setValueAt(Object value, int row, int col) {
        //Musi by� String - java wywoluje tez ta funkcje i przekazuje tutaj stringa
        if(col == 1) parameters.get(row).set(0, new Double((String) value));
        else {
            //Usuwanie wszystkich parametr�w opr�cz pierwszego (pierwszy to 
            //wsp�czynnik) i dodawanie nowych
            Double temp = parameters.get(row).firstElement();
            parameters.get(row).removeAllElements();
            parameters.get(row).add(temp);
            parameters.get(row).addAll( (Vector<Double>) value);
        }
        fireTableCellUpdated(row, col);
    }
    
    @Override
    public boolean isCellEditable(int row, int col) {
        if(col == 1) {
            return true;
        }
        else {
            return false;
        }
    }
    

    /**
     * Funkcja zeruje wszystkie wsp�czynniki ustawione wcze�niej przez 
     * u�ytkownika
     */
    public void clearParameters() {
        for(Integer i:parameters.keySet()) {
            parameters.get(i).removeAllElements();
            parameters.get(i).add(0.0);
            setValueAt(parameters.get(i).firstElement().toString(), i, 1);
        }
    }
}
