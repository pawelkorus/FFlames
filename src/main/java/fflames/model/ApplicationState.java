package fflames.model;

public class ApplicationState extends AbstractModel {
	private static final long serialVersionUID = 1L;
	
	public static final String APPLICATION_NAME = "applicationName";
	public static final String LOADED_FRACTAL_FILE_PATH = "loadedFractalFile";
	
	public ApplicationState() {
		super();
		setParam(APPLICATION_NAME, "FFlames");
	}
	
	public String getLoadedFractalFilePath() {
		return (String) getParam(LOADED_FRACTAL_FILE_PATH);
	}
	
	public String getApplicationName() {
		return (String) getParam(APPLICATION_NAME);
	}
	
	public boolean isFractalFileLoaded() {
		if(getParam(LOADED_FRACTAL_FILE_PATH) == null) {
			return false;
		} else {
			return true;
		}
	}
}
