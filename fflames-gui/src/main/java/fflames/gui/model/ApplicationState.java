package fflames.gui.model;

import fflames.gui.IVisitable;
import fflames.gui.IVisitor;

/**
 * Stores model objects representing general state of the
 * application
 * 
 * @author Pawel Korus
 */
public class ApplicationState extends AbstractModel implements IVisitable {
	private static final long serialVersionUID = 1L;
	private final AlgorithmConfigurationModel _algorithmConfiguration;
	private final TransformTableModel _transformsModel;
	private final ColorsModel _colorsModel;
	private final RenderedImageModel _renderedImage;
	
	public static final String APPLICATION_NAME = "applicationName";
	public static final String LOADED_FRACTAL_FILE_PATH = "loadedFractalFile";
	
	public ApplicationState() {
		super();
		setParam(APPLICATION_NAME, "FFlames");
		
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
	
	public void reset() {
		setParam(LOADED_FRACTAL_FILE_PATH, null);
		
		_algorithmConfiguration.reset();
		_transformsModel.reset();
	}

	@Override
	public void accept(IVisitor visitor) {
		visitor.handle(_algorithmConfiguration);
		_transformsModel.accept(visitor);
		_colorsModel.accept(visitor);
		visitor.handle(this);
	}
}
