package fflames.gui.model;

import fflames.gui.IVisitableModel;
import fflames.gui.IModelVisitor;

/**
 * Stores model objects representing general state of the
 * application
 * 
 * @author Pawel Korus
 */
public class ApplicationState extends AbstractModel implements IVisitableModel {
	private static final long serialVersionUID = 1L;
	private final AlgorithmConfigurationModel _algorithmConfiguration;
	private final TransformTableModel _transformsModel;
	private final ColorsModel _colorsModel;
	private final RenderedImageModel _renderedImage;
	
	public static final String APPLICATION_NAME = "applicationName";
	public static final String LOADED_FRACTAL_FILE_PATH = "loadedFractalFile";
	public static final String SELECTED_COLORING_INDEX = "selectedColoringIndex";
	public static final String SELECTED_TRANSFORM_INDEX = "selectedTransformIndex";
	
	public ApplicationState() {
		super();
		initParam(APPLICATION_NAME, "FFlames");
		initParam(SELECTED_COLORING_INDEX, IndexValue.InvalidValue);
		initParam(SELECTED_TRANSFORM_INDEX, IndexValue.InvalidValue);
		
		_algorithmConfiguration = new AlgorithmConfigurationModel();
		_transformsModel = new TransformTableModel();
		_colorsModel = new ColorsModel();
		_renderedImage = new RenderedImageModel();
	}
	
	public String getLoadedFractalFilePath() {
		return (String) getParam(LOADED_FRACTAL_FILE_PATH);
	}
	
	public String getApplicationName() {
		return (String) getParam(APPLICATION_NAME);
	}
	
	public boolean isFractalFileLoaded() {
		return getParam(LOADED_FRACTAL_FILE_PATH) != null;
	}
	
	public AlgorithmConfigurationModel getAlgorithmConfigurationModel() {
		return _algorithmConfiguration;
	}
	
	public TransformTableModel getTransformsModel() {
		return _transformsModel;
	}
	
	public ColorsModel getColorsModel() {
		return _colorsModel;
	}
	
	public RenderedImageModel getRenderedImageModel() {
		return _renderedImage;
	}
	
	public IndexValue getSelectedColoringIndex() {
		return (IndexValue) getParam(SELECTED_COLORING_INDEX);
	}
	
	public void setSelectedColoringIndex(IndexValue value) {
		setParam(SELECTED_COLORING_INDEX, value);
	}
	
	public IndexValue getSelectedTransformIndex() {
		return (IndexValue) getParam(SELECTED_TRANSFORM_INDEX);
	}
	
	public void setSelectedTransformIndex(IndexValue value) {
		setParam(SELECTED_TRANSFORM_INDEX, value);
	}
	
	public void reset() {
		setParam(LOADED_FRACTAL_FILE_PATH, null);
		setParam(SELECTED_COLORING_INDEX, IndexValue.InvalidValue);
		setParam(SELECTED_TRANSFORM_INDEX, IndexValue.InvalidValue);
		
		_algorithmConfiguration.reset();
		_transformsModel.reset();
	}

	@Override
	public void accept(IModelVisitor visitor) {
		visitor.handle(_algorithmConfiguration);
		_transformsModel.accept(visitor);
		_colorsModel.accept(visitor);
		visitor.handle(this);
	}
}
