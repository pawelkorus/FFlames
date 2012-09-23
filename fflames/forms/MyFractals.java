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
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import fflames.interfaces.IVariation;
import fflames.model.TransformTableModel;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Dimension;

/**
 * 
 * @author victories
 */
public class MyFractals extends javax.swing.JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7603616574289128827L;

	/** Creates new form MyFractals */
	public MyFractals() {
		super();

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

		fileChooser = new javax.swing.JFileChooser();
		bgrDrawning = new javax.swing.ButtonGroup();
		imageFileChooser = new javax.swing.JFileChooser();
		wybraneFunkcjeLabel = new javax.swing.JLabel();

		fileChooser.setApproveButtonText("Otw�rz");
		fileChooser.setCurrentDirectory(null);
		fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Pliki XML", "xml"));

		imageFileChooser.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
		imageFileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Pliki png", "png"));

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("MyFractals");
		setMinimumSize(new java.awt.Dimension(800, 600));
		setName("mainFrame"); // NOI18N

		wybraneFunkcjeLabel.setText("Wybrane funkcje:");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 214, 341, 423, 0 };
		gridBagLayout.rowHeights = new int[] { 200, 25, 25, 42, 25, 25, 48, 25, 77, 201, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);
		jTP = new javax.swing.JTabbedPane();
		jTP.setPreferredSize(new Dimension(200, 200));
		jTP.setMinimumSize(new Dimension(200, 200));
		opcjeJPanel = new javax.swing.JPanel();
		opcjeJPanel.setPreferredSize(new Dimension(200, 200));
		opcjeJPanel.setMinimumSize(new Dimension(200, 200));
		iloscIteracjiJLabel = new javax.swing.JLabel();
		iloscIteracjiJTextField = new javax.swing.JTextField();
		wielkoscObrJLabel = new javax.swing.JLabel();
		widthJTextField = new javax.swing.JTextField();
		xJLabel = new javax.swing.JLabel();
		hieghtJTextField = new javax.swing.JTextField();
		symetrieJLabel = new javax.swing.JLabel();
		rotationsQuantityJTF = new javax.swing.JTextField();
		jrbDrawFast = new javax.swing.JRadioButton();
		jrbDrawSlow = new javax.swing.JRadioButton();
		wariationsJPanel = new fflames.forms.VariationsEditor();
		wariationsJPanel.setMinimumSize(new Dimension(200, 200));
		coloringJPanel = new fflames.forms.ColouringEditor();
		coloringJPanel.setMinimumSize(new Dimension(200, 200));
		infoJPanel = new javax.swing.JPanel();
		infoJPanel.setMinimumSize(new Dimension(200, 200));
		infoAutorJLabel = new javax.swing.JLabel();
		infoTytulJLabel = new javax.swing.JLabel();
		infoWersjaJLabel = new javax.swing.JLabel();
		infoDesc2JLabel = new javax.swing.JLabel();

		iloscIteracjiJLabel.setText("Ilosc iteracji:");

		iloscIteracjiJTextField.setText("100000");

		wielkoscObrJLabel.setText("Wielko�� obrazka:");

		widthJTextField.setText("640");

		xJLabel.setText("x");

		hieghtJTextField.setText("480");

		symetrieJLabel.setText("Ilo�� obrot�w:");

		rotationsQuantityJTF.setText("0");

		bgrDrawning.add(jrbDrawFast);
		jrbDrawFast.setSelected(true);
		jrbDrawFast.setText("Rysuj szybko");

		bgrDrawning.add(jrbDrawSlow);
		jrbDrawSlow.setText("Rysuj dok�adnie");

		javax.swing.GroupLayout opcjeJPanelLayout = new javax.swing.GroupLayout(opcjeJPanel);
		opcjeJPanel.setLayout(opcjeJPanelLayout);
		opcjeJPanelLayout.setHorizontalGroup(opcjeJPanelLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				opcjeJPanelLayout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								opcjeJPanelLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(
												opcjeJPanelLayout
														.createSequentialGroup()
														.addComponent(iloscIteracjiJLabel)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
														.addComponent(iloscIteracjiJTextField,
																javax.swing.GroupLayout.PREFERRED_SIZE, 91,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(
												opcjeJPanelLayout
														.createSequentialGroup()
														.addComponent(wielkoscObrJLabel)
														.addGap(15, 15, 15)
														.addComponent(widthJTextField,
																javax.swing.GroupLayout.PREFERRED_SIZE, 45,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(xJLabel)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(hieghtJTextField,
																javax.swing.GroupLayout.PREFERRED_SIZE, 44,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(
												opcjeJPanelLayout
														.createSequentialGroup()
														.addComponent(symetrieJLabel)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
														.addComponent(rotationsQuantityJTF,
																javax.swing.GroupLayout.PREFERRED_SIZE, 51,
																javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
						.addGroup(
								opcjeJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(jrbDrawFast).addComponent(jrbDrawSlow))
						.addContainerGap(21, Short.MAX_VALUE)));

		opcjeJPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] { hieghtJTextField,
				widthJTextField });

		opcjeJPanelLayout.setVerticalGroup(opcjeJPanelLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				opcjeJPanelLayout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								opcjeJPanelLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(iloscIteracjiJLabel)
										.addComponent(iloscIteracjiJTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jrbDrawFast))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(
								opcjeJPanelLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(wielkoscObrJLabel)
										.addComponent(hieghtJTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(xJLabel)
										.addComponent(widthJTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jrbDrawSlow))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(
								opcjeJPanelLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(symetrieJLabel)
										.addComponent(rotationsQuantityJTF, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(57, 57, 57)));

		jTP.addTab("Opcje", opcjeJPanel);

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

		infoAutorJLabel.setText("Autor programu: Pawe� Korus (victories@tlen.pl), inf. in�. III r.");

		infoTytulJLabel.setText("Tytu� programu: MyFractals");

		infoWersjaJLabel.setText("Wersja: 1.0");

		infoDesc2JLabel.setText("Semestr letni, 2008.");

		javax.swing.GroupLayout infoJPanelLayout = new javax.swing.GroupLayout(infoJPanel);
		infoJPanel.setLayout(infoJPanelLayout);
		infoJPanelLayout.setHorizontalGroup(infoJPanelLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				infoJPanelLayout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								infoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(infoAutorJLabel).addComponent(infoTytulJLabel)
										.addComponent(infoWersjaJLabel).addComponent(infoDesc2JLabel))
						.addContainerGap(108, Short.MAX_VALUE)));
		infoJPanelLayout.setVerticalGroup(infoJPanelLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				infoJPanelLayout.createSequentialGroup().addGap(14, 14, 14).addComponent(infoTytulJLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(infoAutorJLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(infoWersjaJLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(infoDesc2JLabel).addContainerGap(52, Short.MAX_VALUE)));

		jTP.addTab("O programie", infoJPanel);
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
		transformsList = new JTable();
		transformsList.setPreferredScrollableViewportSize(new Dimension(200, 200));
		transformsList.setShowGrid(false);
		transformsList.setFillsViewportHeight(true);
		transformsList.setShowVerticalLines(false);
		transformsList.setTableHeader(null);

		transformsList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listaFunkcjiScrollPane.setViewportView(transformsList);
		GridBagConstraints gbc_listaFunkcjiScrollPane = new GridBagConstraints();
		gbc_listaFunkcjiScrollPane.anchor = GridBagConstraints.NORTH;
		gbc_listaFunkcjiScrollPane.fill = GridBagConstraints.HORIZONTAL;
		gbc_listaFunkcjiScrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_listaFunkcjiScrollPane.gridx = 2;
		gbc_listaFunkcjiScrollPane.gridy = 0;
		getContentPane().add(listaFunkcjiScrollPane, gbc_listaFunkcjiScrollPane);
		dodajButton = new javax.swing.JButton();

		dodajButton.setText("Dodaj");
		GridBagConstraints gbc_dodajButton = new GridBagConstraints();
		gbc_dodajButton.anchor = GridBagConstraints.NORTH;
		gbc_dodajButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_dodajButton.insets = new Insets(0, 0, 5, 5);
		gbc_dodajButton.gridx = 0;
		gbc_dodajButton.gridy = 1;
		getContentPane().add(dodajButton, gbc_dodajButton);
		rysunekJPanel = new fflames.forms.PreviewJPanel();

		rysunekJPanel.setMinimumSize(new java.awt.Dimension(640, 480));
		// rysunekJPanel.setPreferredSize(new Dimension(1000000, 1000000));
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
		GridBagConstraints gbc_usunButton = new GridBagConstraints();
		gbc_usunButton.anchor = GridBagConstraints.NORTH;
		gbc_usunButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_usunButton.insets = new Insets(0, 0, 5, 5);
		gbc_usunButton.gridx = 0;
		gbc_usunButton.gridy = 2;
		getContentPane().add(usunButton, gbc_usunButton);
		loadFractalFileFromXmlJButton = new javax.swing.JButton();

		loadFractalFileFromXmlJButton.setText("Load fractal from XML file");
		GridBagConstraints gbc_loadFractalFileFromXmlJButton = new GridBagConstraints();
		gbc_loadFractalFileFromXmlJButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_loadFractalFileFromXmlJButton.insets = new Insets(0, 0, 5, 5);
		gbc_loadFractalFileFromXmlJButton.gridx = 0;
		gbc_loadFractalFileFromXmlJButton.gridy = 4;
		getContentPane().add(loadFractalFileFromXmlJButton, gbc_loadFractalFileFromXmlJButton);
		saveFractalToXmlJButton = new javax.swing.JButton();
		saveFractalToXmlJButton.setText("Save fractal as Xml file");
		GridBagConstraints gbc_saveFractalToXmlJButton = new GridBagConstraints();
		gbc_saveFractalToXmlJButton.anchor = GridBagConstraints.NORTH;
		gbc_saveFractalToXmlJButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_saveFractalToXmlJButton.insets = new Insets(0, 0, 5, 5);
		gbc_saveFractalToXmlJButton.gridx = 0;
		gbc_saveFractalToXmlJButton.gridy = 5;
		getContentPane().add(saveFractalToXmlJButton, gbc_saveFractalToXmlJButton);
		rysujButton = new javax.swing.JButton();

		rysujButton.setText("Rysuj");
		rysujButton.setEnabled(false);
		GridBagConstraints gbc_rysujButton = new GridBagConstraints();
		gbc_rysujButton.anchor = GridBagConstraints.NORTH;
		gbc_rysujButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_rysujButton.insets = new Insets(0, 0, 5, 5);
		gbc_rysujButton.gridx = 0;
		gbc_rysujButton.gridy = 7;
		getContentPane().add(rysujButton, gbc_rysujButton);
		saveImageButton = new javax.swing.JButton();

		saveImageButton.setText("Zapisz obrazek");
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
	private javax.swing.ButtonGroup bgrDrawning;
	private fflames.forms.ColouringEditor coloringJPanel;
	private javax.swing.JButton dodajButton;
	private javax.swing.JFileChooser fileChooser;
	private javax.swing.JTextField hieghtJTextField;
	private javax.swing.JLabel iloscIteracjiJLabel;
	private javax.swing.JTextField iloscIteracjiJTextField;
	private javax.swing.JFileChooser imageFileChooser;
	private javax.swing.JLabel infoAutorJLabel;
	private javax.swing.JLabel infoDesc2JLabel;
	private javax.swing.JPanel infoJPanel;
	private javax.swing.JLabel infoTytulJLabel;
	private javax.swing.JLabel infoWersjaJLabel;
	private javax.swing.JTabbedPane jTP;
	private javax.swing.JRadioButton jrbDrawFast;
	private javax.swing.JRadioButton jrbDrawSlow;
	private JTable transformsList;
	private javax.swing.JScrollPane listaFunkcjiScrollPane;
	private javax.swing.JPanel opcjeJPanel;
	private javax.swing.JButton loadFractalFileFromXmlJButton;
	private javax.swing.JTextField rotationsQuantityJTF;
	private javax.swing.JButton rysujButton;
	private fflames.forms.PreviewJPanel rysunekJPanel;
	private javax.swing.JButton saveImageButton;
	private javax.swing.JLabel symetrieJLabel;
	private javax.swing.JButton usunButton;
	private fflames.forms.VariationsEditor wariationsJPanel;
	private javax.swing.JTextField widthJTextField;
	private javax.swing.JLabel wielkoscObrJLabel;
	private javax.swing.JLabel wybraneFunkcjeLabel;
	private javax.swing.JLabel xJLabel;
	private javax.swing.JButton saveFractalToXmlJButton;
	private JPanel _affineTranformTab;
	private JTextField _tfPropability;
	private AffineTransformEditor _affineTransformEditor;

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

	public int getSelectedTransform() {
		return transformsList.getSelectedRow();
	}

	public Integer getIterationsNumber() {
		return Integer.parseInt(iloscIteracjiJTextField.getText());
	}

	public Integer getImageWidth() {
		return Integer.parseInt(widthJTextField.getText());
	}

	public Integer getImageHeight() {
		return Integer.parseInt(hieghtJTextField.getText());
	}

	public void setTransformTableModel(TransformTableModel model) {
		transformsList.setModel(model);
		model.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				TransformTableModel model = (TransformTableModel) e.getSource();
				rysujButton.setEnabled(!(model.getRowCount() == 0));
			}
		});
	}

	public void addLoadFractalFileXmlActionListener(ActionListener listener) {
		loadFractalFileFromXmlJButton.addActionListener(listener);
	}

	public void addSaveFractalFileXmlActionListener(ActionListener listener) {
		saveFractalToXmlJButton.addActionListener(listener);
	}

	public void addFunctionActionListener(ActionListener listener) {
		dodajButton.addActionListener(listener);
	}

	public void addRemoveActionListener(ActionListener listener) {
		usunButton.addActionListener(listener);
	}

	public void addDrawActionListener(ActionListener listener) {
		rysujButton.addActionListener(listener);
	}

	public void addSaveImageActionListener(ActionListener listener) {
		saveImageButton.addActionListener(listener);
	}

	public void addTransformsListSelectionListener(ListSelectionListener listener) {
		transformsList.getSelectionModel().addListSelectionListener(listener);
	}

	public PreviewJPanel getRysunekJPanel() {
		return rysunekJPanel;
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
}