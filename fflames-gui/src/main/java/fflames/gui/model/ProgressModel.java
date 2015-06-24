package fflames.gui.model;

import java.io.Serializable;

public class ProgressModel extends AbstractModel implements Serializable {
	public static final String PROGRESS = "progress";
	public static final String START_PROGRESS_VALUE = "start_progress_value";
	public static final String END_PROGRESS_VALUE = "end_progress_value";

	public ProgressModel() {
		super();
		initParam(PROGRESS, (Integer) 0);
		initParam(START_PROGRESS_VALUE, (Integer) 0);
		initParam(END_PROGRESS_VALUE, (Integer) 100);
	}
	
	public int getStartProgressValue() {
		return (Integer)getParam(START_PROGRESS_VALUE);
	}
	
	public void setStartProgressValue(int value) {
		setParam(START_PROGRESS_VALUE, value);
	}
	
	public int getEndProgressValue() {
		return (Integer)getParam(END_PROGRESS_VALUE);
	}
	
	public void setEndProgressValue(int value) {
		setParam(END_PROGRESS_VALUE, value);
	} 
	
	public int getProgress() {
		return (Integer)getParam(PROGRESS);
	}
	
	public void setProgress(int value) {
		if(value < getStartProgressValue()) {
			setParam(PROGRESS, getStartProgressValue());
		} else if(value > getEndProgressValue()) {
			setParam(PROGRESS, getEndProgressValue());
		} else {
			setParam(PROGRESS, value);
		}
	}
	
	public void reset() {
		setProgress(getStartProgressValue());
	}
}
