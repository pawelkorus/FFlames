/*
 * VariationsEditor.java
 *
 * Created on March 18, 2008, 4:10 PM
 */

package fflames.forms;

import java.util.Vector;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import fflames.interfaces.IVariation;
import fflames.model.VariationsFactory;
import fflames.model.VariationsTableModel;
import javax.swing.BoxLayout;

/**
 * 
 * @author victories
 */
public class VariationsEditor extends javax.swing.JPanel {
	private static final long serialVersionUID = 2305910609219372143L;
	private VariationsTableModel _model;

	/** Creates new form VariationsEditor */
	public VariationsEditor() {
		_model = new VariationsTableModel();
		
		initComponents();

		ListSelectionModel lsm = wariationsJTable.getSelectionModel();
		lsm.addListSelectionListener(new WariationJTableListSelectionHandler());
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		wariationsTableJScrollPane = new javax.swing.JScrollPane();
		wariationsJTable = new javax.swing.JTable();

		wariationsJTable.setModel(_model);
		wariationsTableJScrollPane.setViewportView(wariationsJTable);
		add(wariationsTableJScrollPane);
	}// </editor-fold>//GEN-END:initComponents

	/**
	 * Funkcja zwracaj�ca wybrane przez u�ytkownika wariacje. Wybrane przez
	 * u�ytkownika wariacje, to wariacje, dla kt�rych u�ytkownik ustawi� warto��
	 * wsp�czynnika wi�ksz� od zera.
	 * 
	 * @return Obiekt typu Vector<IVariation> zawieraj�cy wariacje wybrane przez
	 *         u�ytkownika
	 * @throws java.lang.Exception
	 */
	public Vector<IVariation> getVariations() {
		Vector<IVariation> wariations = new Vector<IVariation>();
		VariationsTableModel tableModel = (VariationsTableModel) wariationsJTable.getModel();
		IVariation temp;

		for (int i = 0; i < tableModel.getRowCount(); i++) {
			temp = (IVariation) tableModel.getWariation(i);
			if (temp.getCoefficient() != 0.0) {
				wariations.add(temp);
			}
		}

		return wariations;
	}

	/**
	 * Sets model values according to the given variations vector
	 * 
	 * @param variations
	 *            variations vector
	 */
	public void setVariations(Vector<IVariation> variations) {
		((VariationsTableModel) wariationsJTable.getModel()).clearParameters();

		for (IVariation variation : variations) {
			wariationsJTable.getModel().setValueAt(variation.getCoefficient().toString(),
					VariationsFactory.getWariationNumber(variation.getWariationName()), 1);
			if (variation.getParametersQuantity() > 0) {
				wariationsJTable.getModel().setValueAt(variation.getParameters(),
						VariationsFactory.getWariationNumber(variation.getWariationName()), 0);
			}
		}
	}
	
	public VariationsTableModel getModel() {
		return _model;
	}

	private javax.swing.JTable wariationsJTable;
	private javax.swing.JScrollPane wariationsTableJScrollPane;

	// End of variables declaration//GEN-END:variables

	class WariationJTableListSelectionHandler implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
		}
	}
}
