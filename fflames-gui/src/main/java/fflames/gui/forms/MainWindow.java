package fflames.gui.forms;

import fflames.base.IVariation;
import fflames.base.coloring.ColoringFactory;
import fflames.gui.action.Draw;
import fflames.gui.model.AffineTransformModel;
import fflames.gui.model.AlgorithmConfigurationModel;
import fflames.gui.model.ApplicationState;
import fflames.gui.model.ColorsModel;
import fflames.gui.model.IndexValue;
import fflames.gui.model.ProgressModel;
import fflames.gui.model.RenderedImageModel;
import fflames.gui.model.TransformTableModel;
import fflames.gui.model.VariationsTableModel;
import fflames.gui.ui.BasicMainWindowUI;
import fflames.gui.ui.MainWindowUI;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.JComponent;
import javax.swing.ListSelectionModel;
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
	private final TransformTableModel _transformsModel;
	private final ApplicationState _state;
	private final AffineTransformModel _affineTransformModel;
	private final AlgorithmConfigurationModel _algorithmConfigurationModel;
	private final ProgressModel _progressModel;
	private final RenderedImageModel _renderedImageModel;
	private final VariationsTableModel _variationsModel;
	private final ColorsModel _colorsModel;
	private final ActionMap _actions;
	
	public enum ActionId {
		AddTransform,
		RemoveTransform,
		DrawFractal
	}
	
	public MainWindow(
			ApplicationState appState,
			ExecutorService threadPool,
			ActionMap actions) {
		super();
		
		_state = appState;
		_transformsModel = _state.getTransformsModel();
		_algorithmConfigurationModel = _state.getAlgorithmConfigurationModel();
		_renderedImageModel = _state.getRenderedImageModel();
		_colorsModel = _state.getColorsModel();
	
		_affineTransformModel = new AffineTransformModel();
		
		_progressModel = new ProgressModel();
		
		_variationsModel = new VariationsTableModel();
		
		_actions = new ActionMap();
		_actions.setParent(actions);
		_actions.put(ActionId.AddTransform, new AddTransformAction());
		_actions.put(ActionId.RemoveTransform, new RemoveTransformAction());
		
		
		_actions.put(ActionId.DrawFractal, new Draw(_state, threadPool, _progressModel));
		
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
	
	public Action getAction(Object actionId) {
		return _actions.get(actionId);
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
				
				_state.setSelectedColoringIndex(new IndexValue(index));
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
				
				_state.setSelectedTransformIndex(new IndexValue(index));

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
			IndexValue v = _state.getSelectedTransformIndex();
			
			if(v != IndexValue.InvalidValue) {
				_transformsModel.remove(v.toInt());
			}
		}
	}
}
