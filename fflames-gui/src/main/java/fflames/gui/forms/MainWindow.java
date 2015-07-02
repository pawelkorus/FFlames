package fflames.gui.forms;

import fflames.base.FractalGenerator;
import fflames.base.IColoring;
import fflames.base.IVariation;
import fflames.base.coloring.ColoringFactory;
import fflames.gui.model.AffineTransformModel;
import fflames.gui.model.AlgorithmConfigurationModel;
import fflames.gui.model.ApplicationState;
import fflames.gui.model.ColorsModel;
import fflames.gui.model.ProgressModel;
import fflames.gui.model.RenderedImageModel;
import fflames.gui.model.TransformTableModel;
import fflames.gui.model.VariationsTableModel;
import fflames.gui.ui.BasicMainWindowUI;
import fflames.gui.ui.MainWindowUI;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.JComponent;
import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Application main window component
 * 
 * @author Pawel Korus
 */
public class MainWindow extends JComponent {
	/**
	 * The UI class ID string
	 */
	private static final String uiClassID = "FFlamesMainWidnowUI";
	private int _selectedTransformIndex;
	private int _selectedColoringIndex;
	private final TransformTableModel _transformsModel;
	private final ApplicationState _state;
	private final AffineTransformModel _affineTransformModel;
	private final AlgorithmConfigurationModel _algorithmConfigurationModel;
	private final ProgressModel _progressModel;
	private final RenderedImageModel _renderedImageModel;
	private final VariationsTableModel _variationsModel;
	private final ColorsModel _colorsModel;
	private final ExecutorService _threadPool;
	private final ActionMap _actions;
	
	public enum ActionId {
		OpenProjectFile,
		OpenRecentProjectFile,
		SaveProjectFile,
		ExitApplication,
		NewProject,
		SaveFractalImage,
		AddTransform,
		RemoveTransform,
		DrawFractal
	}
	
	public MainWindow(
			ApplicationState appState,
			ExecutorService threadPool) {
		super();
		
		_state = appState;
		_transformsModel = _state.getTransformsModel();
		_algorithmConfigurationModel = _state.getAlgorithmConfigurationModel();
		_renderedImageModel = _state.getRenderedImageModel();
		_colorsModel = _state.getColorsModel();
		
		_threadPool = threadPool;
		
		_affineTransformModel = new AffineTransformModel();
		
		_progressModel = new ProgressModel();
		
		_variationsModel = new VariationsTableModel();
		
		_selectedColoringIndex = -1;
		_selectedTransformIndex = -1;
		
		_actions = new ActionMap();
		_actions.put(ActionId.AddTransform, new AddTransformAction());
		_actions.put(ActionId.RemoveTransform, new RemoveTransformAction());
		_actions.put(ActionId.DrawFractal, new DrawAction());
		
		updateUI();
	}
	
	public void setUI(MainWindowUI ui) {
		super.setUI(ui);
	}
	
	@Override
	public void updateUI() {
		if (UIManager.get(getUIClassID()) != null) {
			setUI((MainWindowUI) UIManager.getUI(this));
		} else {
			setUI(new BasicMainWindowUI());
		}
	}
	
	public MainWindowUI getUI() {
		return (MainWindowUI) ui;
	}
	
	@Override
	public String getUIClassID() {
		return uiClassID;
	}
	
	public void reset() {
		_affineTransformModel.reset();
		
		_variationsModel.reset();
		
		_state.reset();
		
		_progressModel.reset();
		
		_selectedColoringIndex = -1;
		_selectedTransformIndex = -1;
	}
	
	public TransformTableModel getTransformsModel() {
		return _transformsModel;
	}
	
	public AffineTransformModel getAffineTransfomModel() {
		return _affineTransformModel;
	}
	
	public AlgorithmConfigurationModel getAlgorithmConfigurationModel() {
		return _algorithmConfigurationModel;
	}
	
	public ProgressModel getProgressModel() {
		return _progressModel;
	}
	
	public RenderedImageModel getRenderedImageModel() {
		return _renderedImageModel;
	}
	
	public VariationsTableModel getVariationsModel() {
		return _variationsModel;
	}
	
	public ColorsModel getColorsModel() {
		return _colorsModel;
	}
	
	public ListSelectionListener createTransformListSelectionListener() {
		return new TransformListSelectionListener();
	}
	
	public ListSelectionListener createColoringMethodSelectionListener() {
		return new ColoringMethodSelectionListener();
	}
	
	public void setAction(ActionId actionId, Action action) {
		_actions.put(actionId, action);
	}
	
	public Action getAction(ActionId actionId) {
		switch(actionId) {
			case OpenProjectFile:
				return _actions.get(ActionId.OpenProjectFile);
			case OpenRecentProjectFile:
				return _actions.get(ActionId.OpenRecentProjectFile);
			case SaveProjectFile:
				return _actions.get(ActionId.SaveProjectFile);
			case ExitApplication:
				return _actions.get(ActionId.ExitApplication);
			case NewProject:
				return _actions.get(ActionId.NewProject);
			case SaveFractalImage:
				return _actions.get(ActionId.SaveFractalImage);
			case AddTransform:
				return new AddTransformAction();
			case RemoveTransform:
				return new RemoveTransformAction();
			case DrawFractal:
				return new DrawAction();
			default:
				return null;
		}
	}
	
	private class DrawingWorker extends SwingWorker<BufferedImage, Integer> {

		@Override
		protected BufferedImage doInBackground() {	
			ColoringFactory colorsFactory = new ColoringFactory();
			
			ArrayList<Color> selectedColors = new ArrayList<>();
			for(int i = 0; i < _colorsModel.getSize(); i++) {
				float[] components = _colorsModel.getElementAt(i);
				selectedColors.add(new Color(
						components[0],
						components[1],
						components[2]
				));
			}
			
			IColoring coloringMethod = colorsFactory.getColoring(_selectedColoringIndex, selectedColors);
			
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
	
	private class ColoringMethodSelectionListener implements ListSelectionListener {
		@Override public void valueChanged(ListSelectionEvent e) {
			if(e.getValueIsAdjusting()) {
				ListSelectionModel source = (ListSelectionModel)e.getSource();
				
				int index = -1;
				for(int i = e.getFirstIndex(); i <= e.getLastIndex(); i++) {
					if(source.isSelectedIndex(i)) {
						index = i;
						break;
					}
				}
				
				if(index <= -1) {
					return;
				}
				
				ColoringFactory factory = new ColoringFactory();
				boolean showEditor = 
						factory.getColoring(index, null)
								.supportsCustomColors();
				
				if(showEditor) {
					_colorsModel.setSize(_transformsModel.getRowCount());
				} else {
					_colorsModel.setSize(0);
				}
				
				_selectedColoringIndex = index;
			}
		}
	}
	
	private class TransformListSelectionListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(e.getValueIsAdjusting()) {
				ListSelectionModel source = (ListSelectionModel)e.getSource();
				
				int index = -1;
				for(int i = e.getFirstIndex(); i <= e.getLastIndex(); i++) {
					if(source.isSelectedIndex(i)) {
						index = i;
						break;
					}
				}
				
				if(index <= -1) {
					return;
				}
				
				_selectedTransformIndex = index;

				double propability = (Double) _transformsModel.getValueAt(index, 0);

				getUI().setFunctionPropability(propability);

				AffineTransform transform = 
						(AffineTransform) _transformsModel.getAffineTransformAt(index);
				_affineTransformModel.setTransform(transform);

				_variationsModel
						.setVariations(_transformsModel.getVariationsAt(index));
			}
		}
	}

	private class AddTransformAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		
		public AddTransformAction() {
			putValue(NAME, "Add transform");
			putValue(SHORT_DESCRIPTION, "Add transform to the algorithm");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Double propability = getUI().getFunctionPropability();
		
			ArrayList<IVariation> tempVariations = new ArrayList<>();
			_variationsModel.getVariations().stream()
			.filter((variation) -> {
				return variation.getCoefficient() != 0.0;
			})
			.forEach((variation) -> {
				tempVariations.add(variation);
			});

			_transformsModel.addNew(_affineTransformModel.getTransform(), tempVariations, propability);
		}
	}
	
	private class RemoveTransformAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		
		public RemoveTransformAction() {
			putValue(NAME, "Remove transform");
			putValue(SHORT_DESCRIPTION, "Remove transform from the algorithm");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(_selectedTransformIndex > -1) {
				_transformsModel.remove(_selectedTransformIndex);
			}
		}
	}
	
	private class DrawAction extends AbstractAction {
		
		public DrawAction() {
			putValue(NAME, "Draw");
			putValue(SHORT_DESCRIPTION, "Draw fractal flame");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(_transformsModel.getRowCount() > 0) {
				MainWindow.DrawingWorker task = new MainWindow.DrawingWorker();
				task.execute();
			}
		}
	}
}
