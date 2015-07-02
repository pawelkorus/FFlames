package fflames.gui.ui;

import fflames.gui.forms.AffineTransformEditor;
import fflames.gui.forms.AlgorithmConfigurationEditor;
import fflames.gui.forms.ColoringEditor;
import fflames.gui.forms.MainWindow;
import fflames.gui.forms.PreviewJPanel;
import fflames.gui.forms.ProgressBar;
import fflames.gui.forms.VariationsEditor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.plaf.ComponentUI;

/**
 * Basic implementation of main window user interface.
 * 
 * @author Pawel Korus
 */
public class BasicMainWindowUI extends MainWindowUI {
	
	public static ComponentUI createUI(JComponent c) {
		return new BasicMainWindowUI();
	}
	
	@Override
	public void installUI(JComponent c) {
		_mainWindow = (MainWindow) c;
		
		//_actions = new MainWindowActions();
		
		JLabel choosenFunctionsLabel = new javax.swing.JLabel();
		choosenFunctionsLabel.setText("Choosen functions:");
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{214, 341, 423, 0};
		gridBagLayout.rowHeights = new int[]{200, 25, 25, 42, 25, 25, 48, 25, 77, 201, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		_mainWindow.setLayout(gridBagLayout);
		
		_jTP = new javax.swing.JTabbedPane();
		_jTP.setPreferredSize(new Dimension(200, 200));
		_jTP.setMinimumSize(new Dimension(200, 200));

		_variationsEditor = new VariationsEditor();
		_variationsEditor.setModel(_mainWindow.getVariationsModel());
		_variationsEditor.setMinimumSize(new Dimension(200, 200));
		
		_coloringJPanel = new ColoringEditor();
		_coloringJPanel.setMinimumSize(new Dimension(200, 200));
		_coloringJPanel.addListSelectionListener(
				_mainWindow.createColoringMethodSelectionListener());
		_coloringJPanel.setModel(_mainWindow.getColorsModel());

		_algorithmConfigurationEditor = new AlgorithmConfigurationEditor();
		_algorithmConfigurationEditor.setModel(_mainWindow.getAlgorithmConfigurationModel());
		_jTP.addTab("Options", null, _algorithmConfigurationEditor, null);

		_affineTranformTab = new JPanel();
		_affineTranformTab.setMinimumSize(new Dimension(200, 200));
		_jTP.addTab("Affine transform", null, _affineTranformTab, null);
		_affineTranformTab.setLayout(new GridLayout(0, 1, 0, 0));

		_affineTransformEditor = new AffineTransformEditor();
		_affineTransformEditor.setModel(_mainWindow.getAffineTransfomModel());
		GridBagLayout gbl_affineTransformEditor = (GridBagLayout) _affineTransformEditor.getLayout();
		gbl_affineTransformEditor.columnWidths = new int[]{0, 31, 0, 26, 0, 0};
		gbl_affineTransformEditor.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 0.0};
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
		GridBagConstraints gbc_tfPropability = new GridBagConstraints();
		gbc_tfPropability.insets = new Insets(0, 0, 5, 0);
		gbc_tfPropability.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfPropability.gridx = 5;
		gbc_tfPropability.gridy = 2;
		_affineTransformEditor.add(_tfPropability, gbc_tfPropability);
		_tfPropability.setColumns(10);

		JButton _btnRandom = new JButton("Random");
		GridBagConstraints gbc_btnRandom = new GridBagConstraints();
		gbc_btnRandom.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRandom.gridx = 5;
		gbc_btnRandom.gridy = 3;
		_affineTransformEditor.add(_btnRandom, gbc_btnRandom);
		_jTP.addTab("Variations", _variationsEditor);
		_jTP.addTab("Coloring Methods", _coloringJPanel);
		GridBagConstraints gbc_jTP = new GridBagConstraints();
		gbc_jTP.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTP.anchor = GridBagConstraints.NORTH;
		gbc_jTP.insets = new Insets(0, 0, 5, 5);
		gbc_jTP.gridwidth = 2;
		gbc_jTP.gridx = 0;
		gbc_jTP.gridy = 0;
		_mainWindow.add(_jTP, gbc_jTP);

		_transformsListScrollPane = new javax.swing.JScrollPane();
		_transformsListScrollPane.setMinimumSize(new Dimension(200, 200));
		_transformsListScrollPane.setMaximumSize(new Dimension(200, 200));
		_transformsList = new JTable();
		_transformsList.setModel(_mainWindow.getTransformsModel());
		_transformsList.setPreferredScrollableViewportSize(new Dimension(200, 200));
		_transformsList.setShowGrid(false);
		_transformsList.setFillsViewportHeight(true);
		_transformsList.setShowVerticalLines(false);
		_transformsList.setTableHeader(null);
		_transformsList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		_transformsList.getSelectionModel()
				.addListSelectionListener(
						_mainWindow.createTransformListSelectionListener());
		_transformsListScrollPane.setViewportView(_transformsList);
		GridBagConstraints gbc_transformsListScrollPane = new GridBagConstraints();
		gbc_transformsListScrollPane.anchor = GridBagConstraints.NORTH;
		gbc_transformsListScrollPane.fill = GridBagConstraints.HORIZONTAL;
		gbc_transformsListScrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_transformsListScrollPane.gridx = 2;
		gbc_transformsListScrollPane.gridy = 0;
		_mainWindow.add(_transformsListScrollPane, gbc_transformsListScrollPane);

		_addButton = new javax.swing.JButton();
		_addButton.setAction(_mainWindow.getAction(MainWindow.ActionId.AddTransform));
		GridBagConstraints gbc_addButton = new GridBagConstraints();
		gbc_addButton.anchor = GridBagConstraints.NORTH;
		gbc_addButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_addButton.insets = new Insets(0, 0, 5, 5);
		gbc_addButton.gridx = 0;
		gbc_addButton.gridy = 1;
		_mainWindow.add(_addButton, gbc_addButton);
		
		_previewJPanel = new PreviewJPanel();
		_previewJPanel.setModel(_mainWindow.getRenderedImageModel());
		_previewJPanel.setMinimumSize(new java.awt.Dimension(640, 480));
		_previewJPanel.setLayout(new java.awt.FlowLayout());
		GridBagConstraints gbc_previewJPanel = new GridBagConstraints();
		gbc_previewJPanel.fill = GridBagConstraints.BOTH;
		gbc_previewJPanel.gridheight = 11;
		gbc_previewJPanel.gridwidth = 2;
		gbc_previewJPanel.gridx = 1;
		gbc_previewJPanel.gridy = 1;
		_mainWindow.add(_previewJPanel, gbc_previewJPanel);

		_removeButton = new javax.swing.JButton();
		_removeButton.setAction(_mainWindow.getAction(MainWindow.ActionId.RemoveTransform));
		GridBagConstraints gbc_removeButton = new GridBagConstraints();
		gbc_removeButton.anchor = GridBagConstraints.NORTH;
		gbc_removeButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_removeButton.insets = new Insets(0, 0, 5, 5);
		gbc_removeButton.gridx = 0;
		gbc_removeButton.gridy = 2;
		_mainWindow.add(_removeButton, gbc_removeButton);

		_loadFractalFileFromXmlJButton = new javax.swing.JButton();
		_loadFractalFileFromXmlJButton.setAction(
				_mainWindow.getAction(MainWindow.ActionId.OpenRecentProjectFile));
		GridBagConstraints gbc_loadFractalFileFromXmlJButton = new GridBagConstraints();
		gbc_loadFractalFileFromXmlJButton.anchor = GridBagConstraints.NORTH;
		gbc_loadFractalFileFromXmlJButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_loadFractalFileFromXmlJButton.insets = new Insets(0, 0, 5, 5);
		gbc_loadFractalFileFromXmlJButton.gridx = 0;
		gbc_loadFractalFileFromXmlJButton.gridy = 4;
		_mainWindow.add(_loadFractalFileFromXmlJButton, gbc_loadFractalFileFromXmlJButton);

		_saveFractalToXmlJButton = new javax.swing.JButton();
		_saveFractalToXmlJButton.setAction(
				_mainWindow.getAction(MainWindow.ActionId.SaveProjectFile));
		GridBagConstraints gbc_saveFractalToXmlJButton = new GridBagConstraints();
		gbc_saveFractalToXmlJButton.anchor = GridBagConstraints.NORTH;
		gbc_saveFractalToXmlJButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_saveFractalToXmlJButton.insets = new Insets(0, 0, 5, 5);
		gbc_saveFractalToXmlJButton.gridx = 0;
		gbc_saveFractalToXmlJButton.gridy = 5;
		_mainWindow.add(_saveFractalToXmlJButton, gbc_saveFractalToXmlJButton);

		_drawButton = new javax.swing.JButton();
		_drawButton.setAction(_mainWindow.getAction(MainWindow.ActionId.DrawFractal));
		GridBagConstraints gbc_drawButton = new GridBagConstraints();
		gbc_drawButton.anchor = GridBagConstraints.NORTH;
		gbc_drawButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_drawButton.insets = new Insets(0, 0, 5, 5);
		gbc_drawButton.gridx = 0;
		gbc_drawButton.gridy = 7;
		_mainWindow.add(_drawButton, gbc_drawButton);

		_saveImageButton = new javax.swing.JButton();
		_saveImageButton.setText("Save image");
		_saveImageButton.setAction(
				_mainWindow.getAction(MainWindow.ActionId.SaveFractalImage));
		GridBagConstraints gbc_saveImageButton = new GridBagConstraints();
		gbc_saveImageButton.anchor = GridBagConstraints.NORTH;
		gbc_saveImageButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_saveImageButton.insets = new Insets(0, 0, 0, 5);
		gbc_saveImageButton.gridx = 0;
		gbc_saveImageButton.gridy = 9;
		_mainWindow.add(_saveImageButton, gbc_saveImageButton);

		_progressBar = new ProgressBar();
		_progressBar.setModel(_mainWindow.getProgressModel());
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.anchor = GridBagConstraints.NORTH;
		gbc_progressBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar.insets = new Insets(0, 5, 5, 5);
		gbc_progressBar.gridx = 0;
		gbc_progressBar.gridy = 11;
		_mainWindow.add(_progressBar, gbc_progressBar);
		
		_transformsList.setModel(_mainWindow.getTransformsModel());
	}
	
	@Override
	public void uninstallUI(JComponent c) {
		_mainWindow = null;
		_jTP = null;
		_variationsEditor = null;
		_coloringJPanel = null;
		_algorithmConfigurationEditor = null;
		_affineTranformTab = null;
		_affineTransformEditor = null;
		_tfPropability = null;
		_transformsListScrollPane = null;
		_transformsList = null;
		_addButton = null;
		_previewJPanel = null;
		_removeButton = null;
		_loadFractalFileFromXmlJButton = null;
		_saveFractalToXmlJButton = null;
		_drawButton = null;
		_saveImageButton = null;
		_progressBar = null;
	}
	
	@Override
	public Double getFunctionPropability() {
		return new Double(_tfPropability.getText());
	}

	@Override
	public void setFunctionPropability(Double v) {
		_tfPropability.setText(v.toString());
	}
	
	private JTabbedPane _jTP;
	private VariationsEditor _variationsEditor;
	private ColoringEditor _coloringJPanel;
	private AlgorithmConfigurationEditor _algorithmConfigurationEditor;
	private JPanel _affineTranformTab;
	private AffineTransformEditor _affineTransformEditor;
	private JTextField _tfPropability;
	private JScrollPane _transformsListScrollPane;
	private JTable _transformsList;
	private JButton _addButton;
	private PreviewJPanel _previewJPanel;
	private JButton _removeButton;
	private JButton _loadFractalFileFromXmlJButton;
	private JButton _saveFractalToXmlJButton;
	private JButton _drawButton;
	private JButton _saveImageButton;
	private ProgressBar _progressBar;
	private MainWindow _mainWindow;
}
