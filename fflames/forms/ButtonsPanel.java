/*
 * ButtonsPanel.java
 *
 * Created on March 18, 2008, 10:18 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fflames.forms;

import javax.swing.JButton;
import java.awt.Color;
import java.util.Vector;
import java.awt.GridLayout;
import javax.swing.JColorChooser;

/**
 * 
 * @author victories
 */
public class ButtonsPanel extends javax.swing.JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3996032934930462604L;
	private Vector<JButton> buttons;

	/** Creates a new instance of ButtonsPanel */
	public ButtonsPanel() {
		super(new GridLayout(1, 1));
		buttons = new Vector<JButton>();
		buttons.setSize(0);
	}

	public void setButtonsQuantity(int ilosc) {
		if (buttons.size() > ilosc) {
			int roz = buttons.size() - ilosc;
			((GridLayout) getLayout()).setRows(ilosc + 1);
			for (int i = 1; i <= roz; i++) {
				buttons.remove(buttons.size() - 1);
				remove(getComponentCount() - 1);
				validate();
			}
		}
		if (buttons.size() < ilosc) {
			((GridLayout) getLayout()).setRows(ilosc);
			for (int i = buttons.size(); i < ilosc; i++) {
				buttons.add(new JButton("Choose color"));
				buttons.get(buttons.size() - 1).addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						((JButton) evt.getSource()).setBackground(JColorChooser.showDialog(null, "Choose color", null));
					}
				});
				add(buttons.get(i));
			}
			validate();
		}
	}

	public Vector<Color> getColors() {
		Vector<Color> colors = new Vector<Color>();
		for (int i = 0; i < buttons.size(); i++) {
			colors.add(buttons.get(i).getBackground());
		}
		return colors;
	}
}
