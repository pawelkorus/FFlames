package fflames.events;

import fflames.events.Action;

public class LoadProject extends Action {
	public LoadProject(Object source, String filePath) {
		super(source, Actions.LoadProject);
		_filePath = filePath;
	}

	public String getFilePath() {
		return _filePath;
	}
	
	private String _filePath;
	private static final long serialVersionUID = 1L;
}
