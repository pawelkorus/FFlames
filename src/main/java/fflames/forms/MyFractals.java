/*
 * MyFractals.java
 *
 * Created on March 5, 2008, 9:43 PM
 */

package fflames.forms;

import java.lang.Double;
import java.util.Vector;
import javax.swing.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

import fflames.MainWindowActions;
import fflames.generator.IVariation;
import fflames.model.ApplicationState;
import fflames.model.RecentOpenedModel;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;

/**
 * 
 * @author victories
 */
public class MyFractals extends javax.swing.JFrame {
	private static final long serialVersionUID = -7603616574289128827L;
	private ApplicationState _state = null;
	private MainWindowActions _actions = null;

	/** Creates new form MyFractals */
	public MyFractals(ApplicationState state) {
		super();
		
		_state = state;
		_state.addPropertyChangeListener(new ApplicationStateListener());
		
		_actions = new MainWindowActions(this);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mnFile.add(_actions.createNewFractalAction());
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		
		mnFile.add(_actions.createOpenAction(""));
		
		mnOpenRecent = new JMenu("Open recent");
		mnFile.add(mnOpenRecent);
		
		JSeparator separator_1 = new JSeparator();
		mnFile.add(separator_1);
		
		mnFile.add(_actions.createSaveAction());
		
		mnFile.add(_actions.createSaveImageAction());
		
		JSeparator separator_2 = new JSeparator();
		mnFile.add(separator_2);
		
		mnFile.add(_actions.createExitAction());
		
		mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		
		mnAbout.add(_actions.createShowAboutAction());

		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		wybraneFunkcjeLabel = new javax.swing.JLabel();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle(_state.getApplicationName());
		setMinimumSize(new java.awt.Dimension(800, 600));
		setName("mainFrame"); // NOI18N

		wybraneFunkcjeLabel.setText("Wybrane funkcje:");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 214, 341, 423, 0 };
		gridBagLayout.rowHeights = new int[] { 200, 25, 25, 42, 25, 25, 48, 25, 77, 201, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);
		jTP = new javax.swing.JTabbedPane();
		jTP.setPreferredSize(new Dimension(200, 200));
		jTP.setMinimumSize(new Dimension(200, 200));
		
		wariationsJPanel = new fflames.forms.VariationsEditor();
		wariationsJPanel.setMinimumSize(new Dimension(200, 200));
		coloringJPanel = new fflames.forms.ColouringEditor();
		coloringJPanel.setMinimumSize(new Dimension(200, 200));

		_algorithmConfigurationEditor = new AlgorithmConfigurationEditor();
		jTP.addTab("Options", null, _algorithmConfigurationEditor, null);
		
		_affineTranformTab = new JPanel();
		_affineTranformTab.setMinimumSize(new Dimension(200, 200));
		jTP.addTab("Affine transform", null, _affineTranformTab, null);
		_affineTranformTab.setLayout(new GridLayout(0, 1, 0, 0));
		
		_affineTransformEditor = new AffineTransformEditor();
		GridBagLayout gbl__affineTransformEditor = (GridBagLayout) _affineTransformEditor.getLayout();
		gbl__affineTransformEditor.columnWidths = new int[] { 0, 31, 0, 26, 0, 0 };
		gbl__affineTransformEditor.columnWeights = new double[] { 0.0, 1.0, 0.0, 1.0, 0.0, 0.0 };
		_affineTranformTab.add(_affineTransformEditor);

		JLabel lblPropability = new JLabel("Propability:");
		GridBagConstraints gbc_lblPropability = new GridBagConstraints();
		gbc_lblPropability.gridwidth = 5;
		gbc_lblPropability.insets = new Insets(0, 0, 5, 5);
		gbc_lblPropability.anchor = GridBagConstraints.EAST;
		gbc_lblPropability.gridx = 0;
		gbc_lblPropability.gridy = 2;
		_affineTransformEditor.add(lblPropability, gbc_lblPropability);

		_tfPropability = new JTextField();
		GridBagConstraints gbc__tfPropability = new GridBagConstraints();
		gbc__tfPropability.insets = new Insets(0, 0, 5, 0);
		gbc__tfPropability.fill = GridBagConstraints.HORIZONTAL;
		gbc__tfPropability.gridx = 5;
		gbc__tfPropability.gridy = 2;
		_affineTransformEditor.add(_tfPropability, gbc__tfPropability);
		_tfPropability.setColumns(10);

		JButton _btnRandom = new JButton("Random");
		GridBagConstraints gbc__btnRandom = new GridBagConstraints();
		gbc__btnRandom.fill = GridBagConstraints.HORIZONTAL;
		gbc__btnRandom.gridx = 5;
		gbc__btnRandom.gridy = 3;
		_affineTransformEditor.add(_btnRandom, gbc__btnRandom);
		jTP.addTab("Wariacje", wariationsJPanel);
		jTP.addTab("Coloring", coloringJPanel);
		GridBagConstraints gbc_jTP = new GridBagConstraints();
		gbc_jTP.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTP.anchor = GridBagConstraints.NORTH;
		gbc_jTP.insets = new Insets(0, 0, 5, 5);
		gbc_jTP.gridwidth = 2;
		gbc_jTP.gridx = 0;
		gbc_jTP.gridy = 0;
		getContentPane().add(jTP, gbc_jTP);
		
		listaFunkcjiScrollPane = new javax.swing.JScrollPane();
		listaFunkcjiScrollPane.setMinimumSize(new Dimension(200, 200));
		listaFunkcjiScrollPane.setMaximumSize(new Dimension(200, 200));
		_transformsList = new JTable();
		_transformsList.setPreferredScrollableViewportSize(new Dimension(200, 200));
		_transformsList.setShowGrid(false);
		_transformsList.setFillsViewportHeight(true);
		_transformsList.setShowVerticalLines(false);
		_transformsList.setTableHeader(null);

		_transformsList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listaFunkcjiScrollPane.setViewportView(_transformsList);
		GridBagConstraints gbc_listaFunkcjiScrollPane = new GridBagConstraints();
		gbc_listaFunkcjiScrollPane.anchor = GridBagConstraints.NORTH;
		gbc_listaFunkcjiScrollPane.fill = GridBagConstraints.HORIZONTAL;
		gbc_listaFunkcjiScrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_listaFunkcjiScrollPane.gridx = 2;
		gbc_listaFunkcjiScrollPane.gridy = 0;
		getContentPane().add(listaFunkcjiScrollPane, gbc_listaFunkcjiScrollPane);
		
		dodajButton = new javax.swing.JButton();
		dodajButton.setText("Dodaj");
		dodajButton.setAction(_actions.createAddTransformAction());
		GridBagConstraints gbc_dodajButton = new GridBagConstraints();
		gbc_dodajButton.anchor = GridBagConstraints.NORTH;
		gbc_dodajButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_dodajButton.insets = new Insets(0, 0, 5, 5);
		gbc_dodajButton.gridx = 0;
		gbc_dodajButton.gridy = 1;
		getContentPane().add(dodajButton, gbc_dodajButton);
		rysunekJPanel = new fflames.forms.PreviewJPanel();

		rysunekJPanel.setMinimumSize(new java.awt.Dimension(640, 480));
		rysunekJPanel.setLayout(new java.awt.FlowLayout());
		GridBagConstraints gbc_rysunekJPanel = new GridBagConstraints();
		gbc_rysunekJPanel.fill = GridBagConstraints.BOTH;
		gbc_rysunekJPanel.gridheight = 9;
		gbc_rysunekJPanel.gridwidth = 2;
		gbc_rysunekJPanel.gridx = 1;
		gbc_rysunekJPanel.gridy = 1;
		getContentPane().add(rysunekJPanel, gbc_rysunekJPanel);
		
		usunButton = new javax.swing.JButton();
		usunButton.setText("Usun");
		usunButton.setAction(_actions.createRemoveTransformAction());
		GridBagConstraints gbc_usunButton = new GridBagConstraints();
		gbc_usunButton.anchor = GridBagConstraints.NORTH;
		gbc_usunButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_usunButton.insets = new Insets(0, 0, 5, 5);
		gbc_usunButton.gridx = 0;
		gbc_usunButton.gridy = 2;
		getContentPane().add(usunButton, gbc_usunButton);
		
		loadFractalFileFromXmlJButton = new javax.swing.JButton();
		loadFractalFileFromXmlJButton.setText("Load fractal from XML file");
		loadFractalFileFromXmlJButton.setAction(_actions.createOpenAction(""));
		GridBagConstraints gbc_loadFractalFileFromXmlJButton = new GridBagConstraints();
		gbc_loadFractalFileFromXmlJButton.anchor = GridBagConstraints.NORTH;
		gbc_loadFractalFileFromXmlJButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_loadFractalFileFromXmlJButton.insets = new Insets(0, 0, 5, 5);
		gbc_loadFractalFileFromXmlJButton.gridx = 0;
		gbc_loadFractalFileFromXmlJButton.gridy = 4;
		getContentPane().add(loadFractalFileFromXmlJButton, gbc_loadFractalFileFromXmlJButton);
		
		saveFractalToXmlJButton = new javax.swing.JButton();
		saveFractalToXmlJButton.setText("Save fractal as Xml file");
		saveFractalToXmlJButton.setAction(_actions.createSaveAction());
		GridBagConstraints gbc_saveFractalToXmlJButton = new GridBagConstraints();
		gbc_saveFractalToXmlJButton.anchor = GridBagConstraints.NORTH;
		gbc_saveFractalToXmlJButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_saveFractalToXmlJButton.insets = new Insets(0, 0, 5, 5);
		gbc_saveFractalToXmlJButton.gridx = 0;
		gbc_saveFractalToXmlJButton.gridy = 5;
		getContentPane().add(saveFractalToXmlJButton, gbc_saveFractalToXmlJButton);
		
		rysujButton = new javax.swing.JButton();
		rysujButton.setText("Rysuj");
		rysujButton.setAction(_actions.createDrawAction());
		GridBagConstraints gbc_rysujButton = new GridBagConstraints();
		gbc_rysujButton.anchor = GridBagConstraints.NORTH;
		gbc_rysujButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_rysujButton.insets = new Insets(0, 0, 5, 5);
		gbc_rysujButton.gridx = 0;
		gbc_rysujButton.gridy = 7;
		getContentPane().add(rysujButton, gbc_rysujButton);
		
		saveImageButton = new javax.swing.JButton();
		saveImageButton.setText("Save image");
		saveImageButton.setAction(_actions.createSaveImageAction());
		GridBagConstraints gbc_saveImageButton = new GridBagConstraints();
		gbc_saveImageButton.anchor = GridBagConstraints.NORTH;
		gbc_saveImageButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_saveImageButton.insets = new Insets(0, 0, 0, 5);
		gbc_saveImageButton.gridx = 0;
		gbc_saveImageButton.gridy = 9;
		getContentPane().add(saveImageButton, gbc_saveImageButton);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private fflames.forms.ColouringEditor coloringJPanel;
	private javax.swing.JButton dodajButton;
	private javax.swing.JTabbedPane jTP;
	private JTable _transformsList;
	private javax.swing.JScrollPane listaFunkcjiScrollPane;
	private javax.swing.JButton loadFractalFileFromXmlJButton;
	private javax.swing.JButton rysujButton;
	private fflames.forms.PreviewJPanel rysunekJPanel;
	private javax.swing.JButton saveImageButton;
	private javax.swing.JButton usunButton;
	private fflames.forms.VariationsEditor wariationsJPanel;
	private javax.swing.JLabel wybraneFunkcjeLabel;
	private javax.swing.JButton saveFractalToXmlJButton;
	private JPanel _affineTranformTab;
	private JTextField _tfPropability;
	private AffineTransformEditor _affineTransformEditor;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenu mnAbout;
	private JMenu mnOpenRecent;
	private AlgorithmConfigurationEditor _algorithmConfigurationEditor;

	// End of variables declaration//GEN-END:variables

	public void setImage(BufferedImage image) {
		rysunekJPanel.setImage(image);
	}

	public Double getFunctionPropability() {
		return new Double(_tfPropability.getText());
	}

	public void setFunctionPropability(Double v) {
		_tfPropability.setText(v.toString());
	}

	public Vector<IVariation> getVariations() {
		return wariationsJPanel.getVariations();
	}

	public Integer getIterationsNumber() {
		/** 
		 * @todo
		 * return Integer.parseInt(iloscIteracjiJTextField.getText());
		 */
		return 0;
	}

	public Integer getImageWidth() {
		/** 
		 * @todo
		 * return Integer.parseInt(widthJTextField.getText());
		 */
		return 0;
	}

	public Integer getImageHeight() {
		/**
		 * @todo
		 * return Integer.parseInt(hieghtJTextField.getText());
		 */
		
		return 0;
	}
	
	public PreviewJPanel getRysunekJPanel() {
		return rysunekJPanel;
	}

	public JTable getTranformsList() {
		return _transformsList;
	}
	
	public AffineTransformEditor getAffineTransformEditor() {
		return _affineTransformEditor;
	}

	public ColouringEditor getColoringEditor() {
		return coloringJPanel;
	}

	public VariationsEditor getVariationsEditor() {
		return wariationsJPanel;
	}

	public AlgorithmConfigurationEditor getAlgorithmConfigurationEditor() {
		return _algorithmConfigurationEditor;
	}
	
	public void setRecentOpened(final RecentOpenedModel model) {
		for(int i = 0; i < model.getSize(); i++) {
			mnOpenRecent.add(_actions.createOpenAction(model.getElementAt(i)));
		}
		model.addListDataListener(new ListDataListener() {

			@Override
			public void intervalAdded(ListDataEvent e) {
				for(int i = e.getIndex0(); i <= e.getIndex1(); i++) {
					mnOpenRecent.add(new JMenuItem(_actions.createOpenAction(model.getElementAt(i))), i);
				}
			}

			@Override
			public void intervalRemoved(ListDataEvent e) {
				for(int i = e.getIndex0(); i <= e.getIndex1(); i++) {
					mnOpenRecent.remove(e.getIndex0());
				}
			}

			@Override
			public void contentsChanged(ListDataEvent e) {
			}
			
		});
	}
	
	public MainWindowActions getActions() {
		return _actions;
	}
	
	private class ApplicationStateListener implements java.beans.PropertyChangeListener {

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			switch(evt.getPropertyName()) {
			case ApplicationState.LOADED_FRACTAL_FILE_PATH:
				if(_state.getLoadedFractalFilePath().isEmpty()) {
					setTitle(_state.getApplicationName());
				} else {
					setTitle(_state.getApplicationName() + " - " + _state.getLoadedFractalFilePath());
				}
				break;
			}
		}
		
	}
}