/*
 * TextFieldsPanel.java
 *
 * Created on March 11, 2008, 5:39 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fflames.forms;

import javax.swing.*;
import java.awt.*;
import java.util.*;
/**
 *
 * @author victories
 */
public class TextFieldsPanel extends javax.swing.JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2394536362851380440L;
	private Vector<JTextField> kontrolki; 
    /** Creates a new instance of TextFieldsPanel */
    public TextFieldsPanel() {
        super(new GridLayout(1, 1));
        
        kontrolki = new Vector<JTextField>();
        kontrolki.setSize(0);
    }
    
    public void valueTextField(int ilosc) {
        removeAll();
        kontrolki.removeAllElements();
        ((GridLayout) getLayout()).setRows(ilosc);
        for(int i=0; i<ilosc; i++) {
            kontrolki.add(new JTextField("0"));
            add(kontrolki.get(i));
        }
        validate();
    }
    
    public Vector<Double> getTextFieldsValues() throws Exception {
        Vector<Double> values = new Vector<Double>();
        boolean zeros = true;
        for(int i=0; i<kontrolki.size(); i++) {
            values.add(new Double(((JTextField)kontrolki.get(i)).getText()));
            if(values.lastElement() != 0) zeros = false;
        }
        if(zeros) throw new Exception("Nie ustawi�e� parametr�w!");
        return values;
    }
}
