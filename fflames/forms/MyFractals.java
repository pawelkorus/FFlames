/*
 * MyFractals.java
 *
 * Created on March 5, 2008, 9:43 PM
 */

package fflames.forms;

import java.lang.Double;
import java.util.Enumeration;
import java.util.Vector;
import javax.swing.event.*;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import fflames.MainWindowController;
import fflames.interfaces.IVariation;
import fflames.model.RecentOpenedModel;
import fflames.model.TransformTableModel;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Dimension;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Component;
import java.awt.event.ActionEvent;

/**
 * 
 * @author victories
 */
public class MyFractals extends javax.swing.JFrame {
	private static final long serialVersionUID = -7603616574289128827L;

	/** Creates new form MyFractals */
	public MyFractals() {
		super();
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		
		mnFile.add(_openAction);
		
		mnOpenRecent = new JMenu("Open recent");
		mnFile.add(mnOpenRecent);
		
		JSeparator separator_1 = new JSeparator();
		mnFile.add(separator_1);
		
		mnFile.add(_saveAction);
		
		mnFile.add(_saveImageAction);
		
		JSeparator separator_2 = new JSeparator();
		mnFile.add(separator_2);
		
		mnFile.add(_exitAction);
		
		mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		
		mnAbout.add(_showAboutAction);

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
		imageFileChooser = new javax.swing.JFileChooser();
		wybraneFunkcjeLabel = new javax.swing.JLabel();

		fileChooser.setApproveButtonText("Otw�rz");
		fileChooser.setCurrentDirectory(null);
		fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Pliki XML", "xml"));

		imageFileChooser.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
		imageFileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Pliki png", "png"));

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("MyFractals");
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
		wariationsJPanel = new fflames.forms.VariationsEditor();
		wariationsJPanel.setMinimumSize(new Dimension(200, 200));
		coloringJPanel = new fflames.forms.ColouringEditor();
		coloringJPanel.setMinimumSize(new Dimension(200, 200));

		iloscIteracjiJLabel.setText("Ilosc iteracji:");

		iloscIteracjiJTextField.setText("100000");

		wielkoscObrJLabel.setText("Wielko�� obrazka:");

		widthJTextField.setText("640");

		xJLabel.setText("x");

		hieghtJTextField.setText("480");

		symetrieJLabel.setText("Ilo�� obrot�w:");

		rotationsQuantityJTF.setText("0");

		javax.swing.GroupLayout opcjeJPanelLayout = new javax.swing.GroupLayout(opcjeJPanel);
		opcjeJPanelLayout.setHorizontalGroup(opcjeJPanelLayout.createParallelGroup(Alignment.LEADING).addGroup(
				opcjeJPanelLayout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								opcjeJPanelLayout
										.createParallelGroup(Alignment.LEADING)
										.addGroup(
												opcjeJPanelLayout
														.createSequentialGroup()
														.addComponent(iloscIteracjiJLabel)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(iloscIteracjiJTextField,
																GroupLayout.PREFERRED_SIZE, 91,
																GroupLayout.PREFERRED_SIZE))
										.addGroup(
												opcjeJPanelLayout
														.createSequentialGroup()
														.addComponent(wielkoscObrJLabel)
														.addGap(15)
														.addComponent(widthJTextField, GroupLayout.PREFERRED_SIZE, 45,
																GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(xJLabel)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(hieghtJTextField, GroupLayout.PREFERRED_SIZE, 44,
																GroupLayout.PREFERRED_SIZE))
										.addGroup(
												opcjeJPanelLayout
														.createSequentialGroup()
														.addComponent(symetrieJLabel)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(rotationsQuantityJTF, GroupLayout.PREFERRED_SIZE,
																51, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(261, Short.MAX_VALUE)));
		opcjeJPanelLayout.setVerticalGroup(opcjeJPanelLayout.createParallelGroup(Alignment.LEADING).addGroup(
				opcjeJPanelLayout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								opcjeJPanelLayout
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(iloscIteracjiJLabel)
										.addComponent(iloscIteracjiJTextField, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								opcjeJPanelLayout
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(wielkoscObrJLabel)
										.addComponent(hieghtJTextField, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(xJLabel)
										.addComponent(widthJTextField, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								opcjeJPanelLayout
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(symetrieJLabel)
										.addComponent(rotationsQuantityJTF, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(57)));
		opcjeJPanelLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] { widthJTextField, hieghtJTextField });
		opcjeJPanel.setLayout(opcjeJPanelLayout);

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
		GridBagConstraints gbc_usunButton = new GridBagConstraints();
		gbc_usunButton.anchor = GridBagConstraints.NORTH;
		gbc_usunButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_usunButton.insets = new Insets(0, 0, 5, 5);
		gbc_usunButton.gridx = 0;
		gbc_usunButton.gridy = 2;
		getContentPane().add(usunButton, gbc_usunButton);
		
		loadFractalFileFromXmlJButton = new javax.swing.JButton();
		loadFractalFileFromXmlJButton.setText("Load fractal from XML file");
		loadFractalFileFromXmlJButton.setAction(_openAction);
		GridBagConstraints gbc_loadFractalFileFromXmlJButton = new GridBagConstraints();
		gbc_loadFractalFileFromXmlJButton.anchor = GridBagConstraints.NORTH;
		gbc_loadFractalFileFromXmlJButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_loadFractalFileFromXmlJButton.insets = new Insets(0, 0, 5, 5);
		gbc_loadFractalFileFromXmlJButton.gridx = 0;
		gbc_loadFractalFileFromXmlJButton.gridy = 4;
		getContentPane().add(loadFractalFileFromXmlJButton, gbc_loadFractalFileFromXmlJButton);
		
		saveFractalToXmlJButton = new javax.swing.JButton();
		saveFractalToXmlJButton.setText("Save fractal as Xml file");
		saveFractalToXmlJButton.setAction(_saveAction);
		GridBagConstraints gbc_saveFractalToXmlJButton = new GridBagConstraints();
		gbc_saveFractalToXmlJButton.anchor = GridBagConstraints.NORTH;
		gbc_saveFractalToXmlJButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_saveFractalToXmlJButton.insets = new Insets(0, 0, 5, 5);
		gbc_saveFractalToXmlJButton.gridx = 0;
		gbc_saveFractalToXmlJButton.gridy = 5;
		getContentPane().add(saveFractalToXmlJButton, gbc_saveFractalToXmlJButton);
		
		rysujButton = new javax.swing.JButton();
		rysujButton.setText("Rysuj");
		GridBagConstraints gbc_rysujButton = new GridBagConstraints();
		gbc_rysujButton.anchor = GridBagConstraints.NORTH;
		gbc_rysujButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_rysujButton.insets = new Insets(0, 0, 5, 5);
		gbc_rysujButton.gridx = 0;
		gbc_rysujButton.gridy = 7;
		getContentPane().add(rysujButton, gbc_rysujButton);
		
		saveImageButton = new javax.swing.JButton();
		saveImageButton.setText("Save image");
		saveImageButton.setAction(_saveImageAction);
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
	private javax.swing.JFileChooser fileChooser;
	private javax.swing.JTextField hieghtJTextField;
	private javax.swing.JLabel iloscIteracjiJLabel;
	private javax.swing.JTextField iloscIteracjiJTextField;
	private javax.swing.JFileChooser imageFileChooser;
	private javax.swing.JTabbedPane jTP;
	private JTable _transformsList;
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
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenu mnAbout;
	private JMenu mnOpenRecent;
	private MainWindowController _controller;
	private OpenAction _openAction = new OpenAction();
	private SaveAction _saveAction = new SaveAction();
	private SaveImageAction _saveImageAction = new SaveImageAction();
	private ExitAction _exitAction = new ExitAction();
	private ShowAboutAction _showAboutAction = new ShowAboutAction();

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
		return _transformsList.getSelectedRow();
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

	public void addFunctionActionListener(ActionListener listener) {
		dodajButton.addActionListener(listener);
	}

	public void addRemoveActionListener(ActionListener listener) {
		usunButton.addActionListener(listener);
	}

	public void addDrawActionListener(ActionListener listener) {
		rysujButton.addActionListener(listener);
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

	public void setRecentOpened(final RecentOpenedModel model) {
		for(int i = 0; i < model.getSize(); i++) {
			mnOpenRecent.add(new OpenAction(model.getElementAt(i)));
		}
		model.addListDataListener(new ListDataListener() {

			@Override
			public void intervalAdded(ListDataEvent e) {
				for(int i = e.getIndex0(); i <= e.getIndex1(); i++) {
					mnOpenRecent.add(new JMenuItem(new OpenAction(model.getElementAt(i))), i);
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
	
	public void setController(MainWindowController controller) {
		_controller = controller;
	}
	
	private class SaveImageAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public SaveImageAction() {
			putValue(NAME, "Save Image");
			putValue(SHORT_DESCRIPTION, "Save fractal to file");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChooser = new JFileChooser();
	    	fileChooser.setApproveButtonText("Save");
			fileChooser.setCurrentDirectory(null);
			fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("PNG files", "png"));
			int returnValue = fileChooser.showSaveDialog(MyFractals.this);
			if(returnValue == JFileChooser.APPROVE_OPTION) {
				_controller.saveImageFile(fileChooser.getSelectedFile());
			} else {
				return;
			}
		}	
	}
	
	private class SaveAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public SaveAction() {
			putValue(NAME, "Save");
			putValue(SHORT_DESCRIPTION, "Save fractal to file");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChooser = new JFileChooser();
	    	fileChooser.setApproveButtonText("Save");
			fileChooser.setCurrentDirectory(null);
			fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("XML files", "xml"));
			int returnValue = fileChooser.showSaveDialog(MyFractals.this);
			if(returnValue == JFileChooser.APPROVE_OPTION) {
				_controller.saveFractalFile(fileChooser.getSelectedFile().getAbsolutePath());
			} else {
				return;
			}
		}	
	}
	
	private class OpenAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public OpenAction() {
			putValue(NAME, "Open");
			putValue(SHORT_DESCRIPTION, "Open fractal file");
			putValue(ACTION_COMMAND_KEY, "");
		}
		
		public OpenAction(String name) {
			putValue(NAME, name);
			putValue(SHORT_DESCRIPTION, "Open fractal from file");
			putValue(ACTION_COMMAND_KEY, name);
		}
		
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			if(command.isEmpty()) {
				JFileChooser fileChooser = new JFileChooser();
		    	fileChooser.setApproveButtonText("Open");
				fileChooser.setCurrentDirectory(null);
				fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("XML files", "xml"));
				int returnValue = fileChooser.showOpenDialog(MyFractals.this);
				if(returnValue == JFileChooser.APPROVE_OPTION) {
					command = fileChooser.getSelectedFile().getAbsolutePath();
				} else {
					return;
				}
			}
			
			_controller.loadFractalFile(command);
		}
	}

	private class ExitAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public ExitAction() {
			putValue(NAME, "Exit");
			putValue(SHORT_DESCRIPTION, "Exit");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			MyFractals.this.dispose();
		}
		
	}

	private class ShowAboutAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public ShowAboutAction() {
			putValue(NAME, "About FFlames");
			putValue(SHORT_DESCRIPTION, "About FFlames");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JDialog dialog = new AboutDialog();
			dialog.setVisible(true);
		}
		
	}
}