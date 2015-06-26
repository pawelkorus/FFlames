package fflames.gui;
import fflames.base.FractalGenerator;
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

import fflames.base.coloring.ColoringFactory;
import fflames.gui.events.LoadProject;
import fflames.gui.exceptions.ImportXMLFractalFileException;
import fflames.gui.forms.AboutDialog;
import fflames.gui.forms.MyFractals;
import fflames.base.IColoring;
import fflames.base.Transform;
import fflames.gui.model.AffineTransformModel;
import fflames.gui.model.AlgorithmConfigurationModel;
import fflames.gui.model.ApplicationState;
import fflames.gui.model.ProgressModel;
import fflames.gui.model.RecentOpenedModel;
import fflames.gui.model.RenderedImageModel;
import fflames.gui.model.TransformTableModel;
import fflames.gui.model.VariationsTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.swing.SwingWorker;

public final class MainWindowController implements ActionListener {
	private TransformTableModel _transformsModel;
	private RecentOpenedModel _recentOpenedModel;
	private MyFractals _view;
	private ApplicationState _state = null;
	private AffineTransformModel _affineTransformModel = null;
	private VariationsTableModel _variationsTableModel = null;
	private AlgorithmConfigurationModel _algorithmConfigurationModel;
	private ProgressModel _progressModel;
	private RenderedImageModel _renderedImageModel;
	private ExecutorService _threadPool;
	
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
		
		_threadPool = Executors.newFixedThreadPool(6);
		
		_view.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				try {
					_threadPool.shutdown();
					_threadPool.awaitTermination(2, TimeUnit.SECONDS);
				} catch(InterruptedException e) {

				} finally {
					if(!_threadPool.isTerminated()) {
						_threadPool.shutdownNow();
					}
				}
			}
		});
		
		_progressModel = new ProgressModel();
		_view.getProgressBar().registerModel(_progressModel);
		
		_renderedImageModel = new RenderedImageModel();
		_view.getPreviewPanel().registerModel(_renderedImageModel);
	}

	public void showMainWindow() {
		_view.setVisible(true);
	}
	
	public void loadFractalFile(String filePath) {
		reset();
		
		ArrayList<Transform> transforms = new ArrayList<>();
		ImportXMLFractalFile importer = new ImportXMLFractalFile();
		
		try {
			importer.load(transforms, filePath);
			
			transforms.stream().forEach((t) -> {
				_transformsModel.add(t);
			});
			
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
	
	public void saveFractalFile() {
		if(_state.isFractalFileLoaded()) {
			saveFractalFile(_state.getLoadedFractalFilePath());
		}
	}
	
	public void saveFractalFile(String filePath) {
		ExportXMLFileFractal exporter = new ExportXMLFileFractal(_transformsModel.getTransforms());
		
		try {
			exporter.save(filePath);
		} catch (IOException exception) {
			JOptionPane.showMessageDialog(_view, "Error when exporting to choosen file", "Export error", JOptionPane.ERROR_MESSAGE);
			exception.printStackTrace();
		}
	}
	
	public void saveImageFile(File file) {
		try {
			ImageIO.write((RenderedImage) _renderedImageModel.getImage(), "png", file);
		} catch (IOException exception) {
			JOptionPane.showMessageDialog(_view, "Error when saving image file", "Export error", JOptionPane.ERROR_MESSAGE);
			exception.printStackTrace();
		}
	}
	
	public void drawFractal() {
		if(_transformsModel.getRowCount() > 0) {
			DrawingWorker task = new DrawingWorker();
			task.execute();
		}
	}
	
	public void addTransform() {
		Double propability = _view.getFunctionPropability();
		_transformsModel.addNew(_affineTransformModel.getTransform(), _variationsTableModel.getVariations(), propability);
	}
	
	public void removeTransform() {
		int selectedIndex = _view.getTranformsList().getSelectedRow();
		_transformsModel.remove(selectedIndex);
	}
	
	public void newFractal() {
		reset();
	}
	
	class ColoringMethodChangeListener implements ListSelectionListener {
		@Override public void valueChanged(ListSelectionEvent e) {
			if(e.getValueIsAdjusting()) {
				int selectedIndex = _view.getColoringEditor().getSelectedIndex();
				if(selectedIndex < 0) return;
				
				ColoringFactory factory = new ColoringFactory();
				boolean showEditor = 
						factory.getColoring(selectedIndex, null)
								.supportsCustomColors();
				
				if(showEditor) {
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
				_affineTransformModel.setTransform(transform);
				
				_variationsTableModel.setVariations(_transformsModel.getVariationsAt(selectedTransform));
			}
		}
	}
	
	class DrawingWorker extends SwingWorker<BufferedImage, Integer> {

		@Override
		protected BufferedImage doInBackground() {	
			ColoringFactory colorsFactory = new ColoringFactory();
			IColoring coloringMethod = colorsFactory.getColoring(_view.getColoringEditor().getSelectedIndex(), _view.getColoringEditor().getSelectedColors()); 
			
			int[] size = { 
				_algorithmConfigurationModel.getImageWidth(),
				_algorithmConfigurationModel.getImageHeight()
			};
			
			FractalGenerator fractalGenerator = new FractalGenerator(
					_transformsModel.getTransforms(), 
					coloringMethod,
					size,
					_algorithmConfigurationModel.getIterationsNumber(),
					_algorithmConfigurationModel.getSuperSampling(),
					_algorithmConfigurationModel.getRotationsNumber(),
					_threadPool);
			
			long startTime = System.nanoTime();

			int p = 0;
			BufferedImage out = new BufferedImage(1, 1, 1);
			
			_progressModel.setStartProgressValue(1);
			_progressModel.setEndProgressValue(_algorithmConfigurationModel.getIterationsNumber());
			_progressModel.reset();
			
			try {
				Future<BufferedImage> f = fractalGenerator.execute();
				while(!f.isDone()) {
					try {
						f.get(10, TimeUnit.MILLISECONDS);
					} catch(TimeoutException e) {
						_progressModel.setProgress(fractalGenerator.getProgress());
					}
				}
				_progressModel.setProgress(100);
				out = f.get();
			} catch(InterruptedException | ExecutionException e) {

			}
			
			long endTime = System.nanoTime();
			long duration = (endTime - startTime)/1000000; // miliseconds
			System.out.println("Generator execution time: " + duration );
						
			return out;
		}
		
		@Override
		protected void done() { 
			// this method is executed on the Dispatch Event thread
			try {
				BufferedImage result = get();
			
				_renderedImageModel.setImage(result);
			} catch(InterruptedException | ExecutionException e) {
				
			}
		}	
	}
	
	private void reset() {
		_transformsModel.reset();
		_view.getTranformsList().clearSelection();
		
		_affineTransformModel.reset();
		
		_variationsTableModel.reset();
		
		_state.setParam(ApplicationState.LOADED_FRACTAL_FILE_PATH, "");
		
		_progressModel.reset();
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
