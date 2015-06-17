package fflames.gui;
import fflames.generator.FractalGenerator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import prefs.Settings;

import fflames.generator.colouring.ColorsFactory;
import fflames.gui.events.LoadProject;
import fflames.gui.exceptions.ImportXMLFractalFileException;
import fflames.gui.forms.AboutDialog;
import fflames.gui.forms.AffineTransformEditor;
import fflames.gui.forms.MyFractals;
import fflames.generator.IColor;
import fflames.gui.interfaces.IMainWindowController;
import fflames.gui.model.AffineTransformModel;
import fflames.gui.model.AlgorithmConfigurationModel;
import fflames.gui.model.ApplicationState;
import fflames.gui.model.RecentOpenedModel;
import fflames.gui.model.TransformTableModel;
import fflames.gui.model.VariationsTableModel;

public final class MainWindowController implements IMainWindowController, ActionListener {
	private TransformTableModel _transformsModel;
	private RecentOpenedModel _recentOpenedModel;
	private MyFractals _view;
	private ApplicationState _state = null;
	private AffineTransformModel _affineTransformModel = null;
	private VariationsTableModel _variationsTableModel = null;
	private AlgorithmConfigurationModel _algorithmConfigurationModel;
	
	MainWindowController(ApplicationState state, AlgorithmConfigurationModel algorithmConfigurationModel, TransformTableModel transformsModel, MyFractals view) {
		_state = state;
		_view = view;
		
		_algorithmConfigurationModel = algorithmConfigurationModel;
    	_transformsModel = transformsModel;
    	_affineTransformModel = _view.getAffineTransformEditor().getModel();
    	_variationsTableModel = _view.getVariationsEditor().getModel();
    	_recentOpenedModel = new RecentOpenedModel(Settings.getInstance().getRecentOpenedPaths(), 10);
    	
    	_view.setRecentOpened(_recentOpenedModel);
    	_view.getActions().addActionListener(this);
		
		_view.getColoringEditor().addListSelectionListener(new ColoringMethodChangeListener());
		
		_view.getTranformsList().setModel(_transformsModel);
		_view.getTranformsList().getSelectionModel().addListSelectionListener(new TransformListSelectionListener());
	}

	public void showMainWindow() {
		_view.setVisible(true);
	}
	
	/* (non-Javadoc)
	 * @see fflames.IMainWindowController#loadFractalFile(java.lang.String)
	 */
	@Override
	public void loadFractalFile(String filePath) {
		ImportXMLFractalFile importer = new ImportXMLFractalFile();
		try {
			importer.load(_transformsModel, filePath);
			_state.setParam(ApplicationState.LOADED_FRACTAL_FILE_PATH, filePath);
			_recentOpenedModel.add(filePath);
		} catch (ImportXMLFractalFileException exception) {
			JOptionPane.showMessageDialog(_view, "Error occured when parsing choosen file", "Import error", JOptionPane.ERROR_MESSAGE);
			exception.printStackTrace();
		} catch (IOException exception) {
			JOptionPane.showMessageDialog(_view, "Error when reading from choosen file", "Import error", JOptionPane.ERROR_MESSAGE);
			exception.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see fflames.IMainWindowController#saveFractalFile()
	 */
	@Override
	public void saveFractalFile() {
		if(_state.isFractalFileLoaded()) {
			saveFractalFile(_state.getLoadedFractalFilePath());
		}
	}
	
	/* (non-Javadoc)
	 * @see fflames.IMainWindowController#saveFractalFile(java.lang.String)
	 */
	@Override
	public void saveFractalFile(String filePath) {
		ExportXMLFileFractal exporter = new ExportXMLFileFractal(_transformsModel.getTransforms());
		
		try {
			exporter.save(filePath);
		} catch (IOException exception) {
			JOptionPane.showMessageDialog(_view, "Error when exporting to choosen file", "Export error", JOptionPane.ERROR_MESSAGE);
			exception.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see fflames.IMainWindowController#saveImageFile(java.io.File)
	 */
	@Override
	public void saveImageFile(File file) {
		try {
			ImageIO.write((RenderedImage) _view.getRysunekJPanel().getImage(), "png", file);
		} catch (IOException exception) {
			JOptionPane.showMessageDialog(_view, "Error when saving image file", "Export error", JOptionPane.ERROR_MESSAGE);
			exception.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see fflames.IMainWindowController#drawFractal()
	 */
	@Override
	public void drawFractal() {
		if(_transformsModel.getRowCount() > 0) {
			ColorsFactory colorsFactory = new ColorsFactory();
			IColor coloringMethod = colorsFactory.getColoring(_view.getColoringEditor().getSelectedIndex(), _view.getColoringEditor().getSelectedColors()); 
			
			FractalGenerator fractalGenerator = new FractalGenerator(_transformsModel.getTransforms(), coloringMethod, _algorithmConfigurationModel.getImageWidth(), _algorithmConfigurationModel.getImageHeight());
			fractalGenerator.setNumberOfIterations(_algorithmConfigurationModel.getIterationsNumber());
			fractalGenerator.setNumberOfRotations(_algorithmConfigurationModel.getRotationsNumber());
			fractalGenerator.setSamples(_algorithmConfigurationModel.getSuperSampling());
			fractalGenerator.execute();
			
			_view.getRysunekJPanel().resetPoints();
			_view.getRysunekJPanel().setImage(fractalGenerator.getOutput());
		}
	}
	
	/* (non-Javadoc)
	 * @see fflames.IMainWindowController#addTransform()
	 */
	@Override
	public void addTransform() {
		AffineTransformModel affineTransformModel = _view.getAffineTransformEditor().getModel();
		Double propability = _view.getFunctionPropability();
		_transformsModel.addNew(affineTransformModel.getTransform(), _view.getVariations(), propability);
	}
	
	/* (non-Javadoc)
	 * @see fflames.IMainWindowController#removeTransform()
	 */
	@Override
	public void removeTransform() {
		int selectedIndex = _view.getTranformsList().getSelectedRow();
		_transformsModel.remove(selectedIndex);
	}
	
	/* (non-Javadoc)
	 * @see fflames.IMainWindowController#newFractal()
	 */
	@Override
	public void newFractal() {
		reset();
	}
	
	class ColoringMethodChangeListener implements ListSelectionListener {
		@Override public void valueChanged(ListSelectionEvent e) {
			if(e.getValueIsAdjusting()) {
				int selectedIndex = _view.getColoringEditor().getSelectedIndex();
				if(selectedIndex < 0) return;
				
				ColorsFactory factory = new ColorsFactory();
				int buttonsNumber = factory.getColoring(selectedIndex, null).getParametersQuantity();
				if(buttonsNumber == 1) {
					_view.getColoringEditor().setNumberOfColorSlots(1);	
				} else if(buttonsNumber == 2) {
					_view.getColoringEditor().setNumberOfColorSlots(_transformsModel.getRowCount());
				} else {
					_view.getColoringEditor().setNumberOfColorSlots(0);
				}
			}
		}
	}
	
	class TransformListSelectionListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(e.getValueIsAdjusting()) {
				int selectedTransform = _view.getTranformsList().getSelectedRow();
				if(selectedTransform < 0) return;
				
				_view.setFunctionPropability((Double) _transformsModel.getValueAt(selectedTransform, 0));
				AffineTransform transform = (AffineTransform) _transformsModel.getAffineTransformAt(selectedTransform);
				AffineTransformEditor affineTransformEditor = _view.getAffineTransformEditor();
				affineTransformEditor.getModel().setTransform(transform);
				_view.getVariationsEditor().setVariations(_transformsModel.getVariationsAt(selectedTransform));
			}
		}
	}
	
	private void reset() {
		_transformsModel.reset();
		_view.getTranformsList().clearSelection();
		
		_affineTransformModel.reset();
		
		_variationsTableModel.reset();
		
		_state.setParam(ApplicationState.LOADED_FRACTAL_FILE_PATH, "");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int id = e.getID();
		
		if(fflames.gui.events.Action.Actions.NewProject.equals(id)) {
			newFractal();
		} else if(fflames.gui.events.Action.Actions.AddTransform.equals(id)) {
			addTransform();
		} else if(fflames.gui.events.Action.Actions.RemoveTransform.equals(id)) {
			removeTransform();
		} else if(fflames.gui.events.Action.Actions.LoadProject.equals(id)) {
			LoadProject evt = (LoadProject) e;
			String filePath = evt.getFilePath();
			
			if(filePath.isEmpty()) {
				JFileChooser fileChooser = new JFileChooser();
		    	fileChooser.setApproveButtonText("Open");
				fileChooser.setCurrentDirectory(null);
				fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("XML files", "xml"));
				int returnValue = fileChooser.showOpenDialog(_view);
				if(returnValue == JFileChooser.APPROVE_OPTION) {
					filePath = fileChooser.getSelectedFile().getAbsolutePath();
				} else {
					return;
				}
			}
				
			loadFractalFile(filePath);
		} else if(fflames.gui.events.Action.Actions.SaveProject.equals(id)) {
			JFileChooser fileChooser = new JFileChooser();
	    	fileChooser.setApproveButtonText("Save");
			fileChooser.setCurrentDirectory(null);
			fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("XML files", "xml"));
			int returnValue = fileChooser.showSaveDialog(_view);
			if(returnValue == JFileChooser.APPROVE_OPTION) {
				saveFractalFile(fileChooser.getSelectedFile().getAbsolutePath());
			} else {
				return;
			}
		} else if(fflames.gui.events.Action.Actions.SaveGeneratedImage.equals(id)) {
			JFileChooser fileChooser = new JFileChooser();
	    	fileChooser.setApproveButtonText("Save");
			fileChooser.setCurrentDirectory(null);
			fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("PNG files", "png"));
			int returnValue = fileChooser.showSaveDialog(_view);
			if(returnValue == JFileChooser.APPROVE_OPTION) {
				saveImageFile(fileChooser.getSelectedFile());
			} else {
				return;
			}
		} else if(fflames.gui.events.Action.Actions.ExitApplication.equals(id)) {
			_view.dispose();
		} else if(fflames.gui.events.Action.Actions.Draw.equals(id)) {
			drawFractal();
		} else if(fflames.gui.events.Action.Actions.ShowAbout.equals(id)) {
			JDialog dialog = new AboutDialog();
			dialog.setVisible(true);
		}
	}
}
