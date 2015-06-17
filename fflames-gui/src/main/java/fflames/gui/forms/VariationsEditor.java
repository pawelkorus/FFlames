/*
 * VariationsEditor.java
 *
 * Created on March 18, 2008, 4:10 PM
 */
package fflames.gui.forms;

import java.util.ArrayList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import fflames.generator.IVariation;
import fflames.gui.model.VariationsTableModel;
import fflames.generator.variation.VariationsFactory;

import javax.swing.BoxLayout;

/**
 *
 * @author victories
 */
public class VariationsEditor extends javax.swing.JPanel {

	private static final long serialVersionUID = 2305910609219372143L;
	private VariationsTableModel _model;

	/**
	 * Creates new form VariationsEditor
	 */
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
	 * u�ytkownika
	 * @throws java.lang.Exception
	 */
	public ArrayList<IVariation> getVariations() {
		ArrayList<IVariation> variations = new ArrayList<>();
		VariationsTableModel tableModel = (VariationsTableModel) wariationsJTable.getModel();
		IVariation temp;

		for (int i = 0; i < tableModel.getRowCount(); i++) {
			temp = (IVariation) tableModel.getWariation(i);
			if (temp.getCoefficient() != 0.0) {
				variations.add(temp);
			}
		}

		return variations;
	}

	/**
	 * Sets model values according to the given variations vector
	 *
	 * @param variations variations vector
	 */
	public void setVariations(ArrayList<IVariation> variations) {
		((VariationsTableModel) wariationsJTable.getModel()).clearParameters();

		for (IVariation variation : variations) {

			wariationsJTable.getModel().setValueAt(
					variation.getName(), 
					VariationsFactory.getVariationIndex(variation.getName()), 
					0
			);
			
			wariationsJTable.getModel().setValueAt(
					Double.toString(variation.getCoefficient()),
					VariationsFactory.getVariationIndex(variation.getName()), 
					1
			);

			wariationsJTable.getModel().setValueAt(
				variation.getParameters(),
				VariationsFactory.getVariationIndex(variation.getName()),
				2
			);
		}
	}

	public VariationsTableModel getModel() {
		return _model;
	}

	private javax.swing.JTable wariationsJTable;
	private javax.swing.JScrollPane wariationsTableJScrollPane;

	// End of variables declaration//GEN-END:variables
	class WariationJTableListSelectionHandler implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
		}
	}
}
